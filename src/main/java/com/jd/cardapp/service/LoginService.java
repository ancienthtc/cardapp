package com.jd.cardapp.service;

import com.jd.cardapp.model.Admin;
import com.jd.cardapp.model.User;

import java.util.Map;

public interface LoginService {

    //用户登录
    User user_login(String tel, String pass);

    Admin admin_login(String admin,String pass);

    //更新密码
    int PasswordReset(String tel,String password);
}
