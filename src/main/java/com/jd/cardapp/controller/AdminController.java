package com.jd.cardapp.controller;

import com.jd.cardapp.common.AdminCheck;
import com.jd.cardapp.model.Card;
import com.jd.cardapp.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CardService cardService;

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


}
