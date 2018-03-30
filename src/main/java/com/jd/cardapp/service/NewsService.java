package com.jd.cardapp.service;

import com.github.pagehelper.PageInfo;
import com.jd.cardapp.model.News;

import java.util.List;

public interface NewsService {


    //资讯列表
    PageInfo<News> getNewsListByPage(String keys, Integer pageNo , Integer pageSize, String begin , String end);

    //获取资讯
    News getNews(Integer id);

    //资讯上传
    int htmlUpload(String title,String author, String detail , String html, Integer type);

    //用户界面资讯
    PageInfo<News> getNewsPages( Integer pageNo , Integer pageSize, Integer type);

    int NewsDel(Integer id);

}
