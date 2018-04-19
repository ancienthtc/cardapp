package com.jd.cardapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/user/home.php";
    }

    /**
     * 如发生错误 转发到这页面
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/error.php")
    public String error(HttpServletResponse response, HttpServletRequest request) {
        return "error";
    }

    @RequestMapping(value = "/info", produces = "text/html;charset=UTF-8;")
    //@ResponseBody
    public String info(String message,String url,HttpServletResponse response)
    {
        //response.setContentType("text/html; charset=gbk");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        try {
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('"+message+"')");
            out.println("window.location.href = '" + url + "'" );
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.flush();
            out.close();
        }catch (Exception e)
        {
            return "redirect:/login/alogin";
        }
        return null;
    }

}