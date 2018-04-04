package com.jd.cardapp.controller;

import com.alibaba.fastjson.JSON;
import com.jd.cardapp.common.AdminCheck;
import com.jd.cardapp.model.Card;
import com.jd.cardapp.model.Message;
import com.jd.cardapp.service.CardService;
import com.jd.cardapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CardService cardService;

    @Autowired
    private MessageService messageService;

    //首页
    @AdminCheck
    @RequestMapping("/home.php")
    public String toIndex()
    {
        return "back/index";
    }

    @AdminCheck
    @RequestMapping("/welcome.php")
    public String toWelcome()
    {
        return "back/welcome";
    }

    @RequestMapping("/mUserList.php")
    public String toUserManagerList()
    {
        return "back/user-list";
    }

    @RequestMapping("/mRecharge.php")
    public String toUserRechargeList()
    {
        return "back/user-recharge";
    }

    //名片

    @RequestMapping("/mCard.php")
    public String toCardManagerList()
    {
        return "back/card-list";
    }

    @RequestMapping("/mCardWait.php")
    public String toCardManagerWait()
    {
        return "back/card-wait";
    }

    @RequestMapping("/mCardCancel.php")
    public String toCardManagerCancel()
    {
        return "back/card-cancel";
    }

    @RequestMapping("/mCard.php/{id}")
    public String toCardManagerDetail(@PathVariable Integer id, Map<String,Object> m, Model model)
    {
        Card card = cardService.getDetail(id);
        m.put("card",card);
        if(card.getFolder1()!=null && card.getPic1()!=null)
        {
            model.addAttribute("url1","/image/card?route="+card.getFolder1()+card.getPic1());
        }
        else
        {
            model.addAttribute("url1","/public/image/default.png");
        }
        if(card.getFolder2()!=null && card.getPic2()!=null)
        {
            model.addAttribute("url2","/image/card?route="+card.getFolder2()+card.getPic2());
        }
        else
        {
            model.addAttribute("url2","/public/image/default.png");
        }
        return "back/card-detail";
    }


    //主题
    //列表
    @AdminCheck
    @RequestMapping("/mIssue.php")
    public String toIssueManagerList()
    {
        return "back/issue-list";
    }

    //待审核
    @AdminCheck
    @RequestMapping("/mIssueWait.php")
    public String toIssueManagerWait()
    {
        return "back/issue-wait-list";
    }

    //已取消
    @AdminCheck
    @RequestMapping("/mIssueCancel.php")
    public String toIssueManagerCancel()
    {
        return "back/issue-cancel-list";
    }


    //留言
    //未回复页面
    @AdminCheck
    @RequestMapping("/messageNo.php")
    public String toMessageNo()
    {
        return "back/message-no-list";
    }

    //未回复列表数据
    @RequestMapping("/messageNoList")
    @ResponseBody
    public String MessageNoList(HttpSession session, String keys, Integer pageNo, Integer pageSize, String begin, String end)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }
        return JSON.toJSONString( messageService.allMessageList(keys,pageNo,pageSize,begin,end,0) );
    }

    //已回复页面
    @AdminCheck
    @RequestMapping("/messageYes.php")
    public String toMessageYes()
    {
        return "back/message-yes-list";
    }

    //已回复列表数据
    @RequestMapping("/messageYesList")
    @ResponseBody
    public String MessageYesList(HttpSession session, String keys, Integer pageNo, Integer pageSize, String begin, String end)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }
        return JSON.toJSONString( messageService.allMessageList(keys,pageNo,pageSize,begin,end,1) );
    }

    //进入留言查看
    @AdminCheck
    @RequestMapping("/messageDetail.php")
    public String toMessageDetail(Integer mid,Map<String,Object> m)
    {
        Message message = messageService.getMessage(mid);
        m.put("message",message);
        return "back/message-detail";
    }

    //进入留言回复
    @AdminCheck
    @RequestMapping("/reply.php")
    public String toReplyPage(Integer mid,Map<String,Object> m)
    {
        Message message = messageService.getMessage(mid);
        //System.out.println(JSON.toJSONString(m));
        m.put("message",message);
        return "back/message-reply";
    }

    //留言删除
    @RequestMapping("/del.do")
    @ResponseBody
    public String messageDel(HttpSession session,Integer id)
    {
        if( session.getAttribute("admin") == null )
        {
            return "false";
        }
        if( messageService.messageDel(id) >0 )
        {
            return "true";
        }
        return "false";
    }

    //回复提交
    @RequestMapping("/reply.do")
    @ResponseBody
    public String getReply(Message message)
    {
        if(messageService.messageReply(message) > 0 )
        {
            return "回复成功";
        }
        //return "redirect:/admin/messageNo.php";
        return "回复失败";
    }


    //报告



    //推广
    //轮播图管理
    @RequestMapping("/carousel.php")
    public String toCarousel()
    {
        return "back/Carousel-figure";
    }

    @RequestMapping("/news.php")
    public String toNewsList()
    {
        return "back/news-list";
    }

}
