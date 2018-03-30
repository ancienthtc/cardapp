package com.jd.cardapp.controller;

import com.alibaba.fastjson.JSON;
import com.jd.cardapp.model.News;
import com.jd.cardapp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /*页面跳转部分*/
    //用户
    @RequestMapping("/newsCenter.php")
    public String toNewsCenter(Integer type, Map<String,Object> m)
    {
        if(type==null)
        {
            m.put("type",-1);
        }
        else
        {
            m.put("type",type);
        }
        return "front/hangyezixun";
    }

    //资讯详情页
    @RequestMapping("/detail.php/{id}")
    public String toNewsDetail(@PathVariable Integer id,Map<String,Object> m)
    {
        News news = newsService.getNews(id);
        m.put("news",news);
        return "front/hangyezixun-list";
    }


    //管理员
    @RequestMapping("/newsAdd.php")
    public String toNewsAdd()
    {
        return "back/news-add";
    }

    /*数据处理*/
    //用户
    @RequestMapping("/newsInfo")
    @ResponseBody
    public String getNewsInfo(Integer pageNo, Integer pageSize,Integer type)
    {

        return JSON.toJSONString( newsService.getNewsPages(pageNo,pageSize,type) );
    }

    //管理员
    //列表
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

    //增加
    @RequestMapping("/add.do")
    @ResponseBody
    public String newsAdd(HttpSession session, String title,String author, String detail , String html, Integer type )
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }
        if( newsService.htmlUpload( title,author,detail,html,type ) > 0 )
        {
            return "true";
        }
        return "false";
    }

    @RequestMapping("/del.do")
    @ResponseBody
    public String newsDel(HttpSession session,Integer id)
    {
        if( session.getAttribute("admin") == null )
        {
            return "false";
        }
        if( newsService.NewsDel(id) >0 )
        {
            return "true";
        }
        return "false";
    }

    //资讯列表(后台) 资讯详情
    @RequestMapping("/newsDetail.php")
    @ResponseBody
    public void showHtml(HttpServletRequest request, HttpServletResponse response, Integer id)
    {
        String absoultePath;
        News news = newsService.getNews(id);
        absoultePath = news.getFolder() + news.getFilename();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        // 2.设置文件头：最后一个参数是设置下载文件名
        //response.setHeader("Content-Disposition", "attachment;fileName=" + file_name);
        ServletOutputStream out;
        try {
            // 通过文件路径获得File对象
            //absoultePath = absoultePath.replace("\\", "/");
            File html_file = new File(absoultePath);
            FileInputStream inputStream = new FileInputStream(html_file);

            // 3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while ((b = inputStream.read(buffer)) != -1) {
                // 4.写到输出流(out)中
                out.write(buffer, 0, b);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
