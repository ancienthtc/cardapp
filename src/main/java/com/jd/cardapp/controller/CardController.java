package com.jd.cardapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.jd.cardapp.common.AdminCheck;
import com.jd.cardapp.common.GlobalExceptionHandler;
import com.jd.cardapp.common.UserCheck;
import com.jd.cardapp.model.Buy;
import com.jd.cardapp.model.Card;
import com.jd.cardapp.model.User;
import com.jd.cardapp.service.CardService;
import com.jd.cardapp.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/card")
public class CardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if(v==null)
                return "";
            return v;
        }
    };

    @Autowired
    private CardService cardService;

    @Autowired
    private TradeService tradeService;

    /*页面跳转部分*/

    /**
     * 名片宝库
     * @return
     */
    @RequestMapping("/cardStock.php")
    public String toCardStock(String belong,Map<String,Object> map)
    {
        if(belong==null)
        {
            map.put("page", 0);
        }
        else
        {
            map.put("page", belong);
        }
        return "front/mingpianbaoku";
    }

    /**
     * 名片宝库--我要发布
     * @return
     */
    @UserCheck
    @RequestMapping("/cardPost.php")
    public String toCardPost()
    {
        return "front/tijiao";
    }

    //用户--图片查看
    @UserCheck
    @RequestMapping("/cardPic.php")
    public String toCardPic(@RequestParam Integer cid,Map<String,Object> m,HttpSession session)
    {
        //m.put("card",cardService.getDetail(cid));
        User user = (User) session.getAttribute("user");
        Card card = cardService.getDetail(cid);
        //免费
        if(card.getBuy()==0)
        {
            m.put("address1","/image/cid?filename="+card.getPic1());
            m.put("address2","/image/cid?filename="+card.getPic2());
            m.put("pic1",card.getPic1());
            m.put("pic2",card.getPic2());
            return "front/card-picture";
        }
        //收费
        //判断是否有权限
        Buy buy = tradeService.checkBuy(user.getId(),cid);
        if(buy!=null)
        {
            m.put("address1","/image/buy/"+user.getId()+"?filename="+buy.getPic1());
            m.put("address2","/image/buy/"+user.getId()+"?filename="+buy.getPic2());
            m.put("pic1",buy.getPic1());
            m.put("pic2",buy.getPic2());
            return "front/card-picture";
        }
        String url = "/card/cardStock.php";
        return "redirect:info?url="+url+"&&message=无权限查看";

    }



    /*功能模块*/
    //用户
    //获取名片列表
    @RequestMapping("/getCardByPart")
    @ResponseBody
    public String getCard(String type , Integer pageNo ,String keys)
    {
        if( type==null || type.equals("买家综合") )
        {
            return JSON.toJSONString( cardService.getCards(null,pageNo,keys),filter );
        }
        return JSON.toJSONString( cardService.getCards(type,pageNo,keys),filter );
    }

    //增加名片
    @PostMapping("/add.do")
    //@ResponseBody
    public String addCard(HttpSession session,Card card, @RequestParam MultipartFile file1, MultipartFile file2)
    {
        Map<String,String> m = new HashMap<>();
        if( session.getAttribute("user") == null )
        {
            m.put("msg","未登录");
            return JSON.toJSONString( m );
        }
        User user = (User) session.getAttribute("user");
        if( file1.getSize()==0 && file2.getSize()==0 )
        {
            m.put("msg","缺少文件");
            return JSON.toJSONString( m );
        }
        card.setUser( user.getId() );
        if( cardService.CardAdd(card,file1,file2) > 0 )
        {
            m.put("msg","提交成功");
            //return JSON.toJSONString( m );
            return "redirect:/info?message="+m.get("msg")+"&&url=/card/cardStock.php";
        }
        //LOGGER.info( JSON.toJSONString(card) );
        //LOGGER.info( file1.getSize() + " " + file2.getSize() + " " + file1.getOriginalFilename() + " " + file2.getOriginalFilename() );
        m.put("msg","提交失败");
        //return JSON.toJSONString( m );
        return "redirect:/info?message="+m.get("msg")+"&&url=/card/cardStock.php";
    }

    //验证名片是否购买
    @RequestMapping("/check.do")
    @ResponseBody
    public String CheckBuy(@RequestParam Integer cid,HttpSession session)
    {
        Map<String,Object> m = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if(user==null)
        {
            m.put("msg","未登录");
            m.put("status",1);
            return JSON.toJSONString(m);
        }
        Card card = cardService.getDetail(cid);
        if(card.getBuy()==0)
        {
            m.put("msg","免费查看");
            m.put("status",0);
            return JSON.toJSONString(m);
        }
        Buy buy = tradeService.checkBuy(user.getId(),cid);
        if( buy==null )
        {
            m.put("msg","尚未购买");
            m.put("status",2);
            return JSON.toJSONString(m);
        }
        else
        {
            m.put("msg","已购买");
            m.put("status",0);
            return JSON.toJSONString(m);
        }
    }

    @RequestMapping("/myCard")
    @ResponseBody
    public String getMyCard(Integer pageNo , Integer pageSize,HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        if( user == null )
        {
            return null;
        }
        return JSON.toJSONString( cardService.myCards(user.getId(),pageNo,pageSize),filter );
    }




    //管理-名片列表
    @RequestMapping("/cardList")
    @ResponseBody
    public String getCardList(HttpSession session, String keys, Integer pageNo , Integer pageSize, String begin , String end,Integer status)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }

        return JSON.toJSONString( cardService.getCardsList(keys,pageNo,pageSize,begin,end,status) ,filter );
    }

    @PostMapping("/sure.do")
    @ResponseBody
    public String card_sure(@RequestParam Integer id)
    {
        if (cardService.CardCheck(1,id)> 0)
        {
            return "true";
        }
        return "false";
    }

    @PostMapping("/cancel.do")
    @ResponseBody
    public String card_cancel(@RequestParam Integer id)
    {
        if (cardService.CardCheck(2,id)> 0)
        {
            return "true";
        }
        return "false";
    }

    @PostMapping("/reset.do")
    public String card_reset(@RequestParam Integer id)
    {
        if (cardService.CardCheck(0,id)> 0)
        {
            return "true";
        }
        return "false";
    }

    @PostMapping("/del.do")
    public String card_delete(@RequestParam Integer id)
    {
        return "";
    }

}
