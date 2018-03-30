package com.jd.cardapp.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.cardapp.config.MyCustomConfig;
import com.jd.cardapp.dao.NewsMapper;
import com.jd.cardapp.model.News;
import com.jd.cardapp.model.NewsExample;
import com.jd.cardapp.service.ImageService;
import com.jd.cardapp.service.NewsService;
import com.jd.cardapp.util.date.DateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;

    @Autowired
    private MyCustomConfig config;

    @Autowired
    private ImageService imageService;

    @Override
    public PageInfo<News> getNewsListByPage(String keys, Integer pageNo, Integer pageSize, String begin, String end) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        String[] key=keys.split("\\s+");
        List<News> newsList = newsMapper.getNewsList(key,begin,end);
        PageInfo<News> result = new PageInfo<>(newsList);
        return result;
    }

    @Override
    public News getNews(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int htmlUpload(String title, String author, String detail, String html, Integer type) {
        PrintWriter out = null;
        String show = "",tag="";
        if( type==News.international_type )
        {
            show = "国际资讯";
            tag = "A";
        }
        else if( type==News.internal_type )
        {
            show = "国内资讯";
            tag = "B";
        }
        else if( type==News.business_type )
        {
            show = "商务资讯";
            tag = "C";
        }
        else if( type==News.exhibition_type )
        {
            show = "会展资讯";
            tag = "D";
        }
        String head = "<head><title>"+show+"</title></head>";
        html = "<html lang='cn' xmlns:th='http://www.thymeleaf.org'>" + head + "<body>" + html + "</body></html>";
        String folderPath = config.getUpload()+config.getNewsfolder()+"//";
        String filename = tag + DateExample.getTimestamp()+".html";

        try {
            File saveDir = new File(folderPath);
            if (!saveDir.exists() && !saveDir.isDirectory()) {
                saveDir.mkdirs();
            }
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(folderPath+filename), "utf-8"));
            out.println(html);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out!=null) out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.println(html);
        File file = new File(folderPath+filename);

        News news = new News();
        news.setTitle(title);
        news.setType(type);
        news.setAuthor(author);
        news.setDetail(detail);
        news.setFolder(folderPath);
        news.setFilename(filename);
        news.setCreatetime(DateExample.getNowTimeByDate());

        int i = newsMapper.insertSelective(news);
        if( i==0 )
        {
            if(file.exists())
            {
                file.delete();
            }
        }
        return i;
    }

    @Override
    public PageInfo<News> getNewsPages(Integer pageNo, Integer pageSize, Integer type) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        if(type != null)
        {
            criteria.andTypeEqualTo(type);
        }
        newsExample.setOrderByClause("type");
        List<News> newsList = newsMapper.selectByExample(newsExample);
        PageInfo<News> result = new PageInfo<>(newsList);
        return result;
    }

    @Override
    @Transactional
    public int NewsDel(Integer id) {
        News news = newsMapper.selectByPrimaryKey(id);
        int i = 0;
        try
        {
            if( (i=newsMapper.deleteByPrimaryKey(id) )> 0 )
            {
                if(imageService.fileDel( news.getFolder()+news.getFilename() ))
                {
                    return i;
                }
            }
            else
            {
                throw new Exception();
            }
        }catch (Exception e)
        {
            return 0;
        }

        return 0;
    }
}
