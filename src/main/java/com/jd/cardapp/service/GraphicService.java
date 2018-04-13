package com.jd.cardapp.service;

import com.github.pagehelper.PageInfo;
import com.jd.cardapp.model.Graphic;
import org.springframework.web.multipart.MultipartFile;

public interface GraphicService {

    //全部列表
    PageInfo<Graphic> graphicList(String keys, Integer pageNo, Integer pageSize, String begin, String end);

    //获取信息
    Graphic getGraphic(Integer id);

    //增加
    int graphicAdd(Graphic graphic, MultipartFile file);

    //删除
    int graphicDel(Integer id);
}
