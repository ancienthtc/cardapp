package com.jd.cardapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsController {

    /*页面跳转部分*/
    @RequestMapping("/newsCenter.php")
    public String toNewsCenter()
    {
        return "front/hangyezixun";
    }

}
