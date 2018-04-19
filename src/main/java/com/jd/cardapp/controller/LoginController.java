package com.jd.cardapp.controller;

import com.alibaba.fastjson.JSON;
import com.jd.cardapp.model.Admin;
import com.jd.cardapp.model.User;
import com.jd.cardapp.service.LoginService;
import com.jd.cardapp.service.UserService;
import com.jd.cardapp.util.StringUtil.GenerateString;
import com.jd.cardapp.util.api.message.SmsVariableDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    /*页面跳转*/
    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login.php")
    public String toLogin()
    {
        return "front/denglu";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping("/register.php")
    public String toRegister()
    {
        return "front/zhuce";
    }

    /* 忘记密码 */
    /**
     * 忘记密码-第一步
     * @return
     */
    @RequestMapping("/findPass.php")
    public String toFindPass()
    {
        return "front/zhmmone";
    }

    /**
     * 忘记密码-第二步
     * @return
     */
    @RequestMapping("/findPass2.php")
    public String toFindPass2(@RequestParam String tel,HttpSession session,Map<String,Object> m)
    {
        if( !userService.checkTelRepeat(tel) )
        {
            session.setAttribute("reset_tel",tel);
            m.put("tel",tel);
            return "front/zhmmtwo";
        }
        return "redirect:/login/findPass.php";
    }

    //短信验证码
    @RequestMapping("/sendCheck.do")
    @ResponseBody
    public String sendCode1(@RequestParam String tel,HttpSession session)
    {
        SmsVariableDemo demo = new SmsVariableDemo();
        String code= new GenerateString().getRandomString_Num(4);
        session.setAttribute("textcode",code);
        session.setMaxInactiveInterval(15 * 60);//session 15分钟时长
        if(demo.SendMessage1(tel,code,"15") )
        {
            return "true";
        }
        return "false";
    }

    @RequestMapping("/telCode.do")
    @ResponseBody
    public String checkTelCode(@RequestParam String tel,@RequestParam String code,HttpSession session)
    {
        Map<String,Object> m = new HashMap<>();
        String textcode=(String)session.getAttribute("textcode");
        String reset_tel = (String) session.getAttribute("reset_tel");
        if( !reset_tel.equals(tel) )
        {
            m.put("status",1);
            m.put("msg","请勿更改手机");
            return JSON.toJSONString(m);
        }
        if(code.equals(textcode) )
        {
            m.put("status",0);
            m.put("msg","验证成功");
            m.put("permit",tel);
            session.setAttribute("step3",m);
            return JSON.toJSONString(m);
        }
        else
        {
            m.put("status",2);
            m.put("msg","验证码错误");
            return JSON.toJSONString(m);
        }
    }

    /**
     * 忘记密码-第三步
     * @return
     */
    @RequestMapping("/findPass3.php")
    public String toFindPass3(@RequestParam String tel,HttpSession session)
    {
        Map<String,Object> map = (HashMap<String, Object>) session.getAttribute("step3");
        String check_tel = map.get("permit").toString();
        if(map==null )
        {
            return "redirect:/login/findPass.php";
        }
        else if( !( check_tel.equals(tel) ) )
        {
            return "redirect:/login/findPass2.php?tel="+tel;
        }
        return "front/zhmmthree";
    }

    @RequestMapping("/passReset.do")
    @ResponseBody
    public String ResetPassword(HttpSession session,@RequestParam String password)
    {
        //session: reset_tel,textcode,step3
        Map<String,Object> map = (HashMap<String, Object>) session.getAttribute("step3");
        String tel = map.get("permit").toString();
        if( loginService.PasswordReset(tel,password) > 0 )
        {
            return "true";
        }
        return "false";
    }

    /**
     * 忘记密码-第四步
     * @return
     */
    @RequestMapping("/findPass4.php")
    public String toFindPass4(HttpSession session)
    {
        if(session.getAttribute("step3")!=null)
        {
            session.removeAttribute("step3");
            session.removeAttribute("reset_tel");
            session.removeAttribute("textcode");
        }
        else
        {
            return "redirect:/login/findPass.php";
        }
        return "front/zhmmfour";
    }

    @RequestMapping("/alogin.php")
    public String toAdminLogin()
    {
        return "back/login";
    }

    @RequestMapping("/out.do")
    public String UserLoginOut(HttpSession session)
    {
        session.removeAttribute("user");
        return "redirect:/login/login.php";
    }

    @RequestMapping("/aout.do")
    public String AdminLoginOut(HttpSession session)
    {
        session.removeAttribute("admin");
        return "redirect:/login/alogin.php";
    }

    /*数据处理部分*/
    @RequestMapping("/login.do")
    @ResponseBody
    public String user_login(HttpSession session, String tel, String password)
    {
        User user = loginService.user_login(tel,password);
        if( user!=null )
        {
            session.setAttribute("user",user);
            return "true";
        }

        return "false";
    }

    @RequestMapping("/alogin.do")
    @ResponseBody
    public String admin_login(HttpSession session, String admin, String password)
    {
        Admin a = loginService.admin_login(admin,password);
        if( a!=null )
        {
            session.setAttribute("admin",a);
            return "true";
        }
        return "false";
    }


    //注册模块
    //验证号码是否重复
    @RequestMapping("/repeat.do")
    @ResponseBody
    public String RepeatTelCheck(@RequestParam String tel)
    {
        if( userService.checkTelRepeat(tel) )
        {
            return "true";
        }
        return "false";
    }

    //短信验证码
    @RequestMapping("/send.do")
    @ResponseBody
    public String sendCode(@RequestParam String tel,HttpSession session)
    {
        SmsVariableDemo demo = new SmsVariableDemo();
        String code= new GenerateString().getRandomString_Num(4);
        session.setAttribute("textcode",code);
        session.setMaxInactiveInterval(15 * 60);//session 15分钟时长
        if(demo.SendMessage(tel,code,"15") )
        {
            return "true";
        }
        return "false";
    }

    @RequestMapping("/reg.do")
    @ResponseBody
    public String register(User user,String code,HttpSession session)
    {
        Map<String,Object> m = new HashMap<>();
        String textcode=(String)session.getAttribute("textcode");
        if(code.equals(textcode))
        {
            session.removeAttribute("textcode");
            if( userService.Register(user) > 0 )
            {
                m.put("status",0);
                m.put("msg","注册成功");
                return JSON.toJSONString(m);
            }
            else
            {
                m.put("status",2);
                m.put("msg","注册失败");
                return JSON.toJSONString(m);
            }
        }
        m.put("status",1);
        m.put("msg","验证码错误");
        return JSON.toJSONString(m);
    }



}
