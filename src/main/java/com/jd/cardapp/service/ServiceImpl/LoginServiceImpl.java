package com.jd.cardapp.service.ServiceImpl;

import com.jd.cardapp.dao.AdminMapper;
import com.jd.cardapp.dao.UserMapper;
import com.jd.cardapp.model.Admin;
import com.jd.cardapp.model.AdminExample;
import com.jd.cardapp.model.User;
import com.jd.cardapp.service.LoginService;
import com.jd.cardapp.util.password.Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AdminMapper adminMapper;

    @Override
    public User user_login(String tel, String pass) {
        pass = Secret.enPass(pass);
        return userMapper.userLogin(tel,pass);
    }

    @Override
    public Admin admin_login(String admin, String pass) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andAdminEqualTo(admin);
        criteria.andApassEqualTo(Secret.enPass(pass));
        List<Admin> a = adminMapper.selectByExample(adminExample);
        if(a.size()>=1)
        {
            return a.get(0);
        }
        return null;
    }
}
