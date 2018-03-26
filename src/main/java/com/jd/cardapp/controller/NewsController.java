package com.jd.cardapp.controller;

import com.alibaba.fastjson.JSON;
import com.jd.cardapp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /*页面跳转部分*/
    @RequestMapping("/newsCenter.php")
    public String toNewsCenter()
    {
        return "front/hangyezixun";
    }

    /*数据处理*/
    @RequestMapping("/newsList")
    @ResponseBody
    public String getNewsList(HttpSession session,String keys, Integer pageNo, Integer pageSize, String begin, String end)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }
        return JSON.toJSONString( newsService.getNewsListByPage(keys,pageNo,pageSize,begin,end) );
    }

}
