package com.jd.cardapp.service;

import com.github.pagehelper.PageInfo;
import com.jd.cardapp.model.News;

import java.util.List;

public interface NewsService {

    //资讯增加
    int NewsAdd(News news);

    //资讯列表
    PageInfo<News> getNewsListByPage(String keys, Integer pageNo , Integer pageSize, String begin , String end);

    //获取资讯
    News getNews(Integer id);

}
