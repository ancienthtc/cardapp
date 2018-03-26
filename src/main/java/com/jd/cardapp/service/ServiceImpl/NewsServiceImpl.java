package com.jd.cardapp.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.cardapp.model.News;
import com.jd.cardapp.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Override
    public int NewsAdd(News news) {
        return 0;
    }

    @Override
    public PageInfo<News> getNewsListByPage(String keys, Integer pageNo, Integer pageSize, String begin, String end) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        String[] key=keys.split("\\s+");

        return null;
    }

    @Override
    public News getNews(Integer id) {
        return null;
    }
}
