package com.jd.cardapp.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.cardapp.config.MyCustomConfig;
import com.jd.cardapp.dao.BuyMapper;
import com.jd.cardapp.dao.IncomeMapper;
import com.jd.cardapp.dao.UserFileMapper;
import com.jd.cardapp.dao.UserMapper;
import com.jd.cardapp.model.*;
import com.jd.cardapp.service.UserService;
import com.jd.cardapp.util.date.DateExample;
import com.jd.cardapp.util.password.Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private IncomeMapper incomeMapper;

    @Resource
    private BuyMapper buyMapper;

    @Resource
    private UserFileMapper userFileMapper;

    @Autowired
    private MyCustomConfig config;

    @Override
    public int UserUpdate(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<User> getUserList(String keys, Integer pageNo , Integer pageSize, String begin , String end) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        String[] key=keys.split("\\s+");
        /*  无效
        Date s = DateExample.getLocalDate(start);
        Date e = DateExample.getLocalDate(end);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria1 = userExample.createCriteria();
        UserExample.Criteria criteria2 = userExample.createCriteria();
        //UserExample.Criteria criteria3 = userExample.createCriteria();
        for ( int i=0;i<key.length;i++ )
        {
            criteria1.andCreatetimeBetween(s,e);
            criteria1.andRealnameLike("%"+key[i]+"%");

            criteria2.andCreatetimeBetween(s,e);
            criteria2.andConameLike("%"+key[i]+"%");

        }
        userExample.or(criteria2);
        */

        List<User> userList = userMapper.getUserList(key,begin,end);
        PageInfo<User> result = new PageInfo<>(userList);
        return result;
    }

    @Override
    public boolean checkTelRepeat(String tel) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andTelEqualTo(tel);
        List<User> utel = userMapper.selectByExample(userExample);
        if(utel.size()==0)
        {
            return true;
        }
        return false;
    }

    @Override
    public int Register(User user) {
        if( user.getTel()==null || user.getPassword()==null)
        {
            return 0;
        }
        user.setNickname("新用户"+DateExample.getTimestamp());
        user.setCreatetime(DateExample.getNowTimeByDate());
        user.setPassword(Secret.enPass(user.getPassword()));

        return userMapper.insertSelective(user);
    }

    @Override
    public PageInfo<Buy> buyList(Integer uid, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        BuyExample buyExample = new BuyExample();
        BuyExample.Criteria criteria = buyExample.createCriteria();
        criteria.andUserEqualTo(uid);
        List<Buy> buys = buyMapper.selectByExample(buyExample);
        PageInfo<Buy> result=new PageInfo<>(buys);
        return result;
    }

    @Override
    public PageInfo<Income> incomeList(Integer uid, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        IncomeExample incomeExample = new IncomeExample();
        IncomeExample.Criteria criteria = incomeExample.createCriteria();
        criteria.andUserEqualTo(uid);
        List<Income> incomes = incomeMapper.selectByExample(incomeExample);
        PageInfo<Income> result=new PageInfo<>(incomes);
        return result;
    }

    @Override
    public Buy getBuyDetail(Integer id) {
        return buyMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int htmlUpload(Integer uid,String title, String detail, String html, Integer type) {
        PrintWriter out = null;
        String show = "",tag="";
        if( type==0 )
        {
            show = "买家需求"+uid;
            tag = "A";
        }
        else if( type==1 )
        {
            show = "卖家发布"+uid;
            tag = "B";
        }
        else if( type==2 )
        {
            show = "人才招聘"+uid;
            tag = "C";
        }
        String head = "<head><title>"+show+"</title></head>";
        html = "<html lang='cn' xmlns:th='http://www.thymeleaf.org'>" + head + "<body>" + html + "</body></html>";
        String folderPath = config.getUpload()+config.getFilefolder()+uid+"//";
        String filename = tag + DateExample.getTimestamp()+".html";

        System.out.println( html );
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

        UserFile userFile = new UserFile();
        userFile.setType(type);
        userFile.setState(0);
        userFile.setTitle(title);
        userFile.setDetail(detail);
        userFile.setUser(uid);
        userFile.setFilename(filename);
        userFile.setFolder(folderPath);
        userFile.setCreatetime(DateExample.getNowTimeByDate());

        int i = userFileMapper.insertSelective(userFile);
        if( i==0 )
        {
            if(file.exists())
            {
                file.delete();
            }
        }
        return i;
    }
}
