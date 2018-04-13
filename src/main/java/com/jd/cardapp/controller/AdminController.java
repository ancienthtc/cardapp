package com.jd.cardapp.controller;

import com.alibaba.fastjson.JSON;
import com.jd.cardapp.common.AdminCheck;
import com.jd.cardapp.model.Card;
import com.jd.cardapp.model.Graphic;
import com.jd.cardapp.model.Message;
import com.jd.cardapp.model.Picture;
import com.jd.cardapp.service.CardService;
import com.jd.cardapp.service.GraphicService;
import com.jd.cardapp.service.ImageService;
import com.jd.cardapp.service.MessageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CardService cardService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private GraphicService graphicService;

    @Autowired
    private ImageService imageService;

    //首页
    @AdminCheck
    @RequestMapping("/home.php")
    public String toIndex()
    {
        return "back/index";
    }

    @AdminCheck
    @RequestMapping("/welcome.php")
    public String toWelcome()
    {
        return "back/welcome";
    }

    @RequestMapping("/mUserList.php")
    public String toUserManagerList()
    {
        return "back/user-list";
    }

    @RequestMapping("/mRecharge.php")
    public String toUserRechargeList()
    {
        return "back/user-recharge";
    }

    //名片

    @RequestMapping("/mCard.php")
    public String toCardManagerList()
    {
        return "back/card-list";
    }

    @RequestMapping("/mCardWait.php")
    public String toCardManagerWait()
    {
        return "back/card-wait";
    }

    @RequestMapping("/mCardCancel.php")
    public String toCardManagerCancel()
    {
        return "back/card-cancel";
    }

    @RequestMapping("/mCard.php/{id}")
    public String toCardManagerDetail(@PathVariable Integer id, Map<String,Object> m, Model model)
    {
        Card card = cardService.getDetail(id);
        m.put("card",card);
        if(card.getFolder1()!=null && card.getPic1()!=null)
        {
            model.addAttribute("url1","/image/card?route="+card.getFolder1()+card.getPic1());
        }
        else
        {
            model.addAttribute("url1","/public/image/default.png");
        }
        if(card.getFolder2()!=null && card.getPic2()!=null)
        {
            model.addAttribute("url2","/image/card?route="+card.getFolder2()+card.getPic2());
        }
        else
        {
            model.addAttribute("url2","/public/image/default.png");
        }
        return "back/card-detail";
    }


    //主题
    //列表
    @AdminCheck
    @RequestMapping("/mIssue.php")
    public String toIssueManagerList()
    {
        return "back/issue-list";
    }

    //待审核
    @AdminCheck
    @RequestMapping("/mIssueWait.php")
    public String toIssueManagerWait()
    {
        return "back/issue-wait-list";
    }

    //已取消
    @AdminCheck
    @RequestMapping("/mIssueCancel.php")
    public String toIssueManagerCancel()
    {
        return "back/issue-cancel-list";
    }


    //留言
    //未回复页面
    @AdminCheck
    @RequestMapping("/messageNo.php")
    public String toMessageNo()
    {
        return "back/message-no-list";
    }

    //未回复列表数据
    @RequestMapping("/messageNoList")
    @ResponseBody
    public String MessageNoList(HttpSession session, String keys, Integer pageNo, Integer pageSize, String begin, String end)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }
        return JSON.toJSONString( messageService.allMessageList(keys,pageNo,pageSize,begin,end,0) );
    }

    //已回复页面
    @AdminCheck
    @RequestMapping("/messageYes.php")
    public String toMessageYes()
    {
        return "back/message-yes-list";
    }

    //已回复列表数据
    @RequestMapping("/messageYesList")
    @ResponseBody
    public String MessageYesList(HttpSession session, String keys, Integer pageNo, Integer pageSize, String begin, String end)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }
        return JSON.toJSONString( messageService.allMessageList(keys,pageNo,pageSize,begin,end,1) );
    }

    //进入留言查看
    @AdminCheck
    @RequestMapping("/messageDetail.php")
    public String toMessageDetail(Integer mid,Map<String,Object> m)
    {
        Message message = messageService.getMessage(mid);
        m.put("message",message);
        return "back/message-detail";
    }

    //进入留言回复
    @AdminCheck
    @RequestMapping("/reply.php")
    public String toReplyPage(Integer mid,Map<String,Object> m)
    {
        Message message = messageService.getMessage(mid);
        //System.out.println(JSON.toJSONString(m));
        m.put("message",message);
        return "back/message-reply";
    }

    //留言删除
    @RequestMapping("/del.do")
    @ResponseBody
    public String messageDel(HttpSession session,Integer id)
    {
        if( session.getAttribute("admin") == null )
        {
            return "false";
        }
        if( messageService.messageDel(id) >0 )
        {
            return "true";
        }
        return "false";
    }

    //回复提交
    @RequestMapping("/reply.do")
    @ResponseBody
    public String getReply(Message message)
    {
        if(messageService.messageReply(message) > 0 )
        {
            return "回复成功";
        }
        //return "redirect:/admin/messageNo.php";
        return "回复失败";
    }


    //报告
    //进入后台报告
    @AdminCheck
    @RequestMapping("/graphic.php")
    public String toGraphicList( )
    {
        return "back/graphic-list";
    }

    //进入后台报告增加
    @AdminCheck
    @RequestMapping("/graphicAdd.php")
    public String toGraphicAdd()
    {
        return "back/graphic-add";
    }

    //报告列表获取
    @RequestMapping("/graphicList")
    @ResponseBody
    public String getGraphicList(HttpSession session, String keys, Integer pageNo, Integer pageSize, String begin, String end)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }

        return JSON.toJSONString( graphicService.graphicList(keys,pageNo,pageSize,begin,end) );
    }

    @RequestMapping("/graphicAdd.do")
    @ResponseBody
    public String graphic(HttpSession session,Graphic graphic, MultipartFile file)
    {
        if( session.getAttribute("admin") == null )
        {
            return "false";
        }
        if( graphicService.graphicAdd(graphic,file) > 0 )
        {
            return "true";
        }
        return "false";
    }

//    @ResponseBody
//    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
//    public String pdfDownload(Integer id)
//    {
//        Graphic graphic = graphicService.getGraphic(id);
//        String retString = null;
//        String dir = "";
//        String path = graphic.getFolder()+graphic.getFilename();
//        retString = path.replaceAll("\\\\","/");
//        Map<String,Object >map = new HashMap<>();
//        map.put("code",0);
//        map.put("pdf",retString);
//        return JSON.toJSONString(map);
//    }

    @RequestMapping(value="/pdfDownload", method=RequestMethod.GET)
    public void getDownload(HttpSession session,Integer id, HttpServletRequest request, HttpServletResponse response)
    {
        if( session.getAttribute("admin") == null )
        {
            return;
        }

        // Get your file stream from wherever.
        Graphic graphic = graphicService.getGraphic(id);
        if(graphic==null)
        {
            return;
        }

        String fullPath = graphic.getFolder()+graphic.getFilename();
        File downloadFile = new File(fullPath);

        ServletContext context = request.getServletContext();

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
            System.out.println("context getMimeType is null");
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        //String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        //System.out.println( downloadFile.getName() );
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // Copy the stream to the response's output stream.
        try {
            java.io.FileInputStream myStream = new FileInputStream(fullPath);
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/pdfRead.php", method = RequestMethod.GET)
    @ResponseBody
    public void pdfRead(HttpSession session,Integer id, HttpServletResponse response)
    {
        if( session.getAttribute("admin") == null )
        {
            return;
        }
        Graphic graphic = graphicService.getGraphic(id);
        String path = graphic.getFolder()+graphic.getFilename();
        ServletOutputStream out;
        try {
            // 通过文件路径获得File对象
            //absoultePath = absoultePath.replace("\\", "/");
            File html_file = new File(path);
            FileInputStream inputStream = new FileInputStream(html_file);

            // 3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while ((b = inputStream.read(buffer)) != -1) {
                // 4.写到输出流(out)中
                out.write(buffer, 0, b);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //报告删除
    @RequestMapping("/graphicDel.do")
    @ResponseBody
    public String graphicDel(HttpSession session,Integer id)
    {
        if( session.getAttribute("admin") == null )
        {
            return "false";
        }
        if( graphicService.graphicDel(id) > 0 )
        {
            return "true";
        }
        return "false";
    }

    //推广
    //轮播图管理
    @RequestMapping("/carousel.php")
    public String toCarousel()
    {
        return "back/Carousel-figure";
    }

    //资讯管理
    @RequestMapping("/news.php")
    public String toNewsList()
    {
        return "back/news-list";
    }

    //首页图管理
    @RequestMapping("/images.php")
    public String toHomeImages()
    {
        return "back/home-images";
    }

    //首页图添加
    @RequestMapping("/imagesAdd.php")
    public String toHomeImagesAdd()
    {
        return "back/home-images-add";
    }

    //增加
    @RequestMapping("/imagesAdd.do")
    @ResponseBody
    public String HomeImagesAdd(HttpSession session,Picture picture ,MultipartFile file)
    {
        if( session.getAttribute("admin") == null )
        {
            return "false";
        }
        if(file.getSize()==0)
        {
            return "false";
        }
        if(imageService.PictureAdd(picture,file) > 0)
        {
            return "true";
        }
        return "false";
    }

    //删除
    @RequestMapping("/imagesDel.do")
    @ResponseBody
    public String HomeImagesDel(HttpSession session,Integer id)
    {
        if( session.getAttribute("admin") == null )
        {
            return "false";
        }
        if( imageService.PictureDel(id) > 0 )
        {
            return "true";
        }
        return "false";
    }

    //首页图列表
    @RequestMapping("/pictureList")
    @ResponseBody
    public String pictureList(HttpSession session,String keys, Integer pageNo, Integer pageSize, String begin, String end, Integer type)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }
        return JSON.toJSONString( imageService.pictureList(keys,pageNo,pageSize,begin,end,type) );
    }

}
