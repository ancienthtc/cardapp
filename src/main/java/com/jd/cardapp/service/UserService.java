package com.jd.cardapp.service;

import com.github.pagehelper.PageInfo;
import com.jd.cardapp.model.*;

import java.util.List;

public interface UserService {

    int UserUpdate(User user);

    User getUserById(Integer id);

    PageInfo<User> getUserList(String keys, Integer pageNo , Integer pageSize, String begin , String end);

    boolean checkTelRepeat(String tel);

    int Register(User user);


    PageInfo<Buy> buyList(Integer uid, Integer pageNo , Integer pageSize);

    PageInfo<Income> incomeList(Integer uid, Integer pageNo , Integer pageSize);

    Buy getBuyDetail(Integer id);

    int htmlUpload(Integer uid ,String title, String detail ,String html,Integer type);

    PageInfo<UserFile> getUserFileList(String keys, Integer pageNo , Integer pageSize, String begin , String end,Integer status);

    int userFileUpdate(Integer id , Integer status);

    UserFile getUserFile(Integer id);

    int UserFileDel(Integer id);

    PageInfo<UserFile> usertGetRequestByType(Integer type,Integer pageNo , Integer pageSize);

    int MessageAdd(Message message);
}
