package com.jd.cardapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.jd.cardapp.common.GlobalExceptionHandler;
import com.jd.cardapp.model.User;
import com.jd.cardapp.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/trade")
public class TradeController {

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
    private TradeService tradeService;

    //新充值列表
    @RequestMapping("/rechageList")
    @ResponseBody
    public String getRechargeList(HttpSession session, Integer pageNo , Integer pageSize, String begin , String end)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }
        return JSON.toJSONString( tradeService.rechargeList(pageNo,pageSize,begin,end),filter );
    }

    @RequestMapping("/withdrawList")
    @ResponseBody
    public String getWithDrawList(HttpSession session, Integer pageNo , Integer pageSize, String begin , String end)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }
        return JSON.toJSONString( tradeService.withdrawList(pageNo,pageSize,begin,end),filter );
    }


    @RequestMapping("/buyCard")
    @ResponseBody
    public String UserBuyCard(HttpSession session, @RequestParam Integer cid)
    {
        Map<String,Object> m = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if( user == null )
        {
            m.put("status",1);
            m.put("msg","未登录");
            return JSON.toJSONString(m);
        }
        m = tradeService.userBuyCard(user.getId(),cid);
        return JSON.toJSONString(m);
    }

}
