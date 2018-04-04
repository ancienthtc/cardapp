package com.jd.cardapp.service;

import com.github.pagehelper.PageInfo;
import com.jd.cardapp.model.Message;

public interface MessageService {

    int MessageAdd(Message message);

    PageInfo<Message> userMessageList(Integer pageNo , Integer pageSize, String name, String tel);

    //管理员
    PageInfo<Message> allMessageList(String keys, Integer pageNo, Integer pageSize, String begin, String end,Integer type);

    Message getMessage(Integer id);

    int messageReply(Message message);

    int messageDel(Integer id);
}
