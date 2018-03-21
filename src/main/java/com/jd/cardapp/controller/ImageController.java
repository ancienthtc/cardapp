package com.jd.cardapp.controller;

import com.jd.cardapp.config.MyCustomConfig;
import com.jd.cardapp.model.Card;
import com.jd.cardapp.model.User;
import com.jd.cardapp.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private MyCustomConfig config;

    /**
     * @param request
     * @param response
     * @param route --> 绝对路径
     */
    @ResponseBody
    @RequestMapping(value = "/card")
    public void showCommonPicture(HttpServletRequest request, HttpServletResponse response, @RequestParam String route)
    {
        String imagePath;
        imagePath = route;

        //System.out.println(imagePath);
        OutPutAgain(imagePath,request,response,0);
    }

    @ResponseBody
    @RequestMapping(value = "/cid")
    public void getPictureByCard(HttpServletRequest request, HttpServletResponse response,@RequestParam String filename)
    {
        if(filename==null || filename.equals("null") || filename=="")
        {
            defaultShow(request, response);
        }
        String basePath = config.getUpload() + config.getCardfolder();
        String imagePath = basePath + filename;
        OutPutAgain(imagePath,request,response,0);
    }

    private void defaultShow(HttpServletRequest request, HttpServletResponse response) {
        String imagePath;
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        imagePath = basePath + "public//image//default.png";

        System.out.println(imagePath);
        OutPutAgain(imagePath,request,response,0);
    }

    @ResponseBody
    @RequestMapping(value = "/buy/{uid}")
    public void getPictureByBuy(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer uid,@RequestParam String filename)
    {
        User user = (User) request.getSession().getAttribute("user");
        if( user!=null )
        {
            if( uid == user.getId() )
            {
                String basePath = config.getUpload() + config.getUserfolder();
                String imagePath = basePath + uid + "//" + filename;
                OutPutAgain(imagePath,request,response,0);
            }
        }
        else
        {
            defaultShow(request, response);
        }

    }


    private void OutPutAgain(String imagePath,HttpServletRequest request, HttpServletResponse response,Integer value)
    {
        FileInputStream in;
        response.setContentType("application/octet-stream;charset=UTF-8");
        try {
            // 图片读取路径
            in = new FileInputStream(imagePath);
            int i = in.available();
            byte[] data = new byte[i];
            in.read(data);
            in.close();

            // 写图片
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        }
        catch (FileNotFoundException e)
        {
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            imagePath = basePath + "public/image/default.png";
            if(value==0)
            {
                OutPutAgain(imagePath,request,response,1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @ResponseBody
    @RequestMapping(value = "/test")
    public void showCommonPicture(HttpServletRequest request, HttpServletResponse response)
    {
        defaultShow(request, response);
    }





}
