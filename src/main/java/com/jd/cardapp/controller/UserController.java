package com.jd.cardapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.github.pagehelper.PageInfo;
import com.jd.cardapp.common.GlobalExceptionHandler;
import com.jd.cardapp.common.UserCheck;
import com.jd.cardapp.config.MyCustomConfig;
import com.jd.cardapp.model.*;
import com.jd.cardapp.model.Advance.DataTablePageUtil;
import com.jd.cardapp.service.*;
import com.jd.cardapp.util.date.DateExample;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if(v==null)
                return "";
            return v;
        }
    };

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private GraphicService graphicService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private MyCustomConfig config;

    /* 页面跳转部分 */
    /**首页
     * @return
     */
    @RequestMapping(value = "/home.php" , produces = "text/html;charset=UTF-8;")
    public String toIndex(Map<String,Object> m) {
        //加载首页图片
        List<Picture> list0 = imageService.getPictureList(0);
        List<Picture> list1 = imageService.getPictureList(1);
        List<Picture> list2 = imageService.getPictureList(2);
        List<Picture> list3 = imageService.getPictureList(3);
        //轮播
        m.put("pic0",list0);
        //专业化服务
        m.put("pic1",list1);
        //行业细分
        m.put("pic2",list2);
        //企业展示
        m.put("pic3",list3);

        //length
        m.put("length1",4-list1.size());
        m.put("length2",list2.size());
        m.put("length3",list3.size());

        return "front/index";
    }

    /**个人中心
     * @return
     */
    @UserCheck
    @RequestMapping("/personal.php")
    public String toPersonal(HttpSession session,HashMap<String, Object> map) {
        User user= (User) session.getAttribute("user");
        map.put("user",user);

        return "front/gerenzhongxin";
    }

    /**个人中心--充值页面
     * @return
     */
    @UserCheck
    @RequestMapping("/recharge.php")
    public String toRecharge()
    {
        return "front/zhanghuchongzhi";
    }

    @RequestMapping("/choosePay.php")
    public String toChoosePay(@RequestParam Double money,Map<String,Object> map)
    {
        map.put("money",money);
        return "front/chongzhi";
    }

    /**个人中心--收益记录
     * @return
     */
    @UserCheck
    @RequestMapping("/income.php")
    public String toIncome()
    {
        return "front/mingpianshouyi";
    }

    /**
     * 个人中心--申请提现
     * @return
     */
    @UserCheck
    @RequestMapping("/withdraw.php")
    public String toWithdraw(HttpSession session,Map<String,Object> m)
    {
        User user = (User) session.getAttribute("user");
        user = userService.getUserById(user.getId());
        session.setAttribute("user",user);

        m.put("user",user);
        if(user.getBalance()<50)
        {
            m.put("limit",0);
        }
        else
        {
            m.put("limit",user.getBalance());
        }
        return "front/shenqingtixian";
    }

    /**
     * 个人中心--提现记录
     * @return
     */
    @UserCheck
    @RequestMapping("/withdrawRecord.php")
    public String toWithdrawRecord()
    {
        return "front/tixianjilv";
    }

    /**
     * 个人中心--我要发布--名片管理
     * @return
     */
    @UserCheck
    @RequestMapping("/myCard.php")
    public String toMyCard()
    {




        return "front/woyaofabu";
    }

    /**
     * 个人中心--我要发布--买家需求
     * @return
     */
    @UserCheck
    @RequestMapping("/buyerRequest.php")
    public String toBuyerRequest()
    {
        return "front/maijiaxuqiu";
    }

    /**
     * 个人中心--我要发布--卖家发布
     * @return
     */
    @UserCheck
    @RequestMapping("/sellerPost.php")
    public String toSellerPost()
    {
        return "front/maijiafabu";
    }

    /**
     * 个人中心--我要发布--人才招聘
     * @return
     */
    @UserCheck
    @RequestMapping("/talent.php")
    public String toTalent()
    {
        return "front/zhaopinrencai";
    }

    /**
     * 个人中心--我要发布--我的数据
     * @return
     */
    @UserCheck
    @RequestMapping("/dataCenter.php")
    public String toMyDataCenter()
    {
        //return "front/wodeshuju";
        return "redirect:/user/myMessage.php";
    }

    /**
     * 个人中心--购买记录
     * @return
     */
    @UserCheck
    @RequestMapping("/buyRecord.php")
    public String toBuyRecord(Map<String,Object> m)
    {

        return "front/goumaijilv";
    }

    /**
     * 个人中心--购买记录--详情
     * @return
     */
    @UserCheck
    @RequestMapping("/buyRecordDetail.php")
    public String toBuyRecordDetail(HttpSession session,Map<String,Object> m,@RequestParam Integer bid)
    {
        User user = (User)  session.getAttribute("user");
        Buy buy = userService.getBuyDetail(bid);
        JSONObject object = JSONObject.parseObject(buy.getDetail()) ;
        m.put("company",object.get("company"));
        m.put("cardmail",object.get("cardmail"));
        m.put("goods",object.get("goods"));
        m.put("belong",object.get("belong"));

        m.put("pic1",buy.getPic1());
        m.put("pic2",buy.getPic2());
        if( buy.getPic1()!=null )
        {
            m.put("img1","/image/buy/"+user.getId()+"?filename="+buy.getPic1());
        }
        if( buy.getPic2()!=null )
        {
            m.put("img2","/image/buy/"+user.getId()+"?filename="+buy.getPic2());
        }

        return "front/buy_detail";
    }

    /**
     * 个人中心-我的数据-我的留言
     * @return
     */
    @RequestMapping("/myMessage.php")
    public String toMyDataMessage()
    {



        return "front/message-search";
    }



    /**
     * 供需平台
     * @return
     */
    @RequestMapping("/platform.php")
    public String toPlatform()
    {
        return "front/gongxupintai";
    }

    /**
     * 数据服务
     * @return
     */
    @RequestMapping("/dataService.php")
    public String toDataService()
    {
        return "front/shujufuwu";
    }

    /**
     * 数据服务--报告下载
     * @return
     */
    @UserCheck
    @RequestMapping("/downReport.php")
    public String toDownloadReport()
    {
        return "front/baogaoxiazai";
    }

    /**
     * 数据服务--其他
     * @return
     */
    @RequestMapping("/message.php")
    public String toMessage()
    {
        return "front/sjfwqt";
    }

    /**
     * 关于我们
     * @return
     */
    @RequestMapping("/about.php")
    public String toCoIntroduceHome()
    {
        return "front/about";
    }

    /**
     * 加入我们
     * @return
     */
    @RequestMapping("/join.php")
    public String toJoinUs()
    {
        return "front/jiaruwomen";
    }

    /*数据获取部分*/
    /*用户*/
    //个人信息修改
    @PostMapping("/updateUser.do")
    @ResponseBody
    public String personal(User user)
    {
        //System.out.println(JSON.toJSON(user));
        if( userService.UserUpdate(user) > 0 )
        {
            return "true";
        }
        return "false";
    }

    //获取购买记录
    @RequestMapping("/buyList")
    @ResponseBody
    public String getBuyList(HttpSession session,Integer pageNo , Integer pageSize)
    {
        User user = (User) session.getAttribute("user");
        if( user == null )
        {
            return null;
        }
        //System.out.println(JSON.toJSONString( userService.buyList(user.getId()) ));
        return JSON.toJSONString( userService.buyList(user.getId(),pageNo,pageSize),filter );
    }

    //收益
    @RequestMapping("/incomeList")
    @ResponseBody
    public String incomeList(HttpSession session,Integer pageNo , Integer pageSize)
    {
        User user = (User) session.getAttribute("user");
        if( user == null )
        {
            return null;
        }
        return JSON.toJSONString( userService.incomeList(user.getId(),pageNo,pageSize),filter );
    }

    //需求发布
    @RequestMapping("/subFile")
    @ResponseBody
    public String requestPost(HttpServletRequest request,String title, String detail ,String html,Integer type )
    {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null)
        {
            return "false";
        }
        if( userService.htmlUpload(user.getId(),title,detail,html,type) > 0 )
        {
            return "true";
        }
        return "false";
    }


    //需求发布详情
    @RequestMapping("/getFileDetail.php")
    @ResponseBody
    public void showHtml(HttpServletRequest request, HttpServletResponse response,Integer id)
    {
        String absoultePath;
        UserFile userFile = userService.getUserFile(id);
        absoultePath = userFile.getFolder() + userFile.getFilename();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        // 2.设置文件头：最后一个参数是设置下载文件名
        //response.setHeader("Content-Disposition", "attachment;fileName=" + file_name);
        ServletOutputStream out;
        try {
            // 通过文件路径获得File对象
            //absoultePath = absoultePath.replace("\\", "/");
            File html_file = new File(absoultePath);
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

    //需求发布查询
    @RequestMapping("/requestList")
    @ResponseBody
    public String userGetRequest(HttpSession session,Integer type , Integer pageNo ,Integer pageSize)
    {
        //暂时不验证登录
//        User user = (User) session.getAttribute("user");
//        if( user == null )
//        {
//            return null;
//        }

        return JSON.toJSONString( userService.usertGetRequestByType(type,pageNo,pageSize) );
    }


    //留言
    //留言提交
    @RequestMapping("/messageAdd.do")
    @ResponseBody
    public String messageSubmit(HttpSession session,Message message)
    {
//        User user = (User) session.getAttribute("user");
//        if( user == null )
//        {
//            return null;
//        }
        if( messageService.MessageAdd(message) > 0 )
        {
            return "true";
        }
        return "false";
    }


    //留言查询
    @RequestMapping("/messageSearch")
    @ResponseBody
    public String userMessageList(Integer pageNo , Integer pageSize,String name,String tel)
    {
        return JSON.toJSONString( messageService.userMessageList(pageNo,pageSize,name,tel) );
    }

    //报告
    //报告检查购买
    @RequestMapping("/reportCheck.do")
    @ResponseBody
    public String graphicCheckBuy(HttpSession session,Integer id)
    {
        Map<String,Object> m = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if(user==null)
        {
            m.put("msg","未登录");
            m.put("status",1);
            return JSON.toJSONString(m);
        }
        Graphic graphic = graphicService.getGraphic(id);
        if(graphic==null)
        {
            m.put("msg","报告不存在");
            m.put("status",2);
            return JSON.toJSONString(m);
        }
        Buy buy = tradeService.checkReport(user.getId(),id);
//        Card card = cardService.getDetail(cid);
//        if(card.getBuy()==0)
//        {
//            m.put("msg","免费查看");
//            m.put("status",0);
//            return JSON.toJSONString(m);
//        }
        if( buy==null )
        {
            m.put("msg","尚未购买");
            m.put("status",3);
            return JSON.toJSONString(m);
        }
        else
        {
            m.put("msg","已购买");
            m.put("status",0);
            return JSON.toJSONString(m);
        }
    }

    //用户报告下载
    @RequestMapping(value="/pdfDownload", method= RequestMethod.GET)
    public void getDownload(HttpSession session,Integer id, HttpServletRequest request, HttpServletResponse response)
    {
        //验证登录
        User user = (User) session.getAttribute("user");
        if( user == null )
        {
            return;
        }


        //验证购买
        Buy buy = tradeService.checkReport(user.getId(),id);
        if(buy==null)
        {
            return;
        }

        // Get your file stream from wherever.
        String fullPath;
        Graphic graphic = graphicService.getGraphic(id);
        if(graphic!=null)
        {
            String sourceFile = graphic.getFilename();
            String targetFile = graphic.getTitle()+sourceFile.substring(sourceFile.lastIndexOf("."));
            fullPath = graphic.getFolder()+sourceFile;
        }
        else
        {
            fullPath = buy.getFolder1()+buy.getPic1();
        }
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

    @RequestMapping("/graphicList")
    @ResponseBody
    public String graphicList(HttpSession session, String keys, Integer pageNo, Integer pageSize)
    {
        User user = (User) session.getAttribute("user");
        if( user == null )
        {
            return null;
        }
        return JSON.toJSONString( graphicService.graphicList(keys,pageNo,pageSize,null,null) );
    }


    //提现
    //提现提交
    @RequestMapping("/withdrawAdd.do")
    @ResponseBody
    public String WithdrawAdd(HttpSession session,Withdraw withdraw)
    {
        User user = (User) session.getAttribute("user");
        Map<String,Object> m = new HashMap<>();
        if(user==null)
        {
            m.put("status",1);
            m.put("msg","未登录");
            return JSON.toJSONString(m);
        }

        if(user.getBalance()<50)
        {
            m.put("status",2);
            m.put("msg","提现金额不足");
            return JSON.toJSONString(m);
        }
        else
        {
            withdraw.setUser(user.getId());
            if( tradeService.WithdrawAdd(withdraw) > 0 )
            {
                m.put("status",0);
                m.put("msg","申请已提交");
                return JSON.toJSONString(m);
            }
            else
            {
                m.put("status",3);
                m.put("msg","提交失败");
                return JSON.toJSONString(m);
            }
        }

    }

    //提现记录
    @RequestMapping("/userWithdrawList")
    @ResponseBody
    public String getUserWithdrawList(HttpSession session,Integer pageNo,Integer pageSize)
    {
        User user = (User) session.getAttribute("user");
        if( user == null )
        {
            return null;
        }
        return JSON.toJSONString( userService.userWithdrawList(user.getId(),pageNo,pageSize) );
    }



    /*管理员*/
    //获取用户列表
    @PostMapping("/getUserList")
    @ResponseBody
    public String getUserList(HttpSession session,String keys, Integer pageNo , Integer pageSize, String begin , String end)
    {
        if( session.getAttribute("admin") == null )
        {
            return null;
        }
        return JSON.toJSONString(userService.getUserList(keys,pageNo,pageSize,begin,end),filter);
    }

    @RequestMapping("/getUserListTable")
    @ResponseBody
    public String getUserList(HttpServletRequest request)
    {
        String keys = request.getParameter("keys");
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");
        DataTablePageUtil<User> dataTable = new DataTablePageUtil<User>(request);
        PageInfo<User> user = userService.getUserList(keys,dataTable.getPage_num(), dataTable.getPage_size(),begin,end);
        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(user.getList());
        dataTable.setRecordsTotal((int) user.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        //使用DataTables的属性接收分页数据
//        DataTablePageUtil<MyBiz> dataTable = new DataTablePageUtil<MyBiz>(request);
//        //开始分页：PageHelper会处理接下来的第一个查询
//        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
//        //还是使用List，方便后期用到
//        List<MyBiz> list = service.getByUserId(user.getId());
//        //用PageInfo对结果进行包装
//        PageInfo<MyBiz> pageInfo = new PageInfo<MyBiz>(list);
//
//        //封装数据给DataTables
//        dataTable.setDraw(dataTable.getDraw());
//        dataTable.setData(pageInfo.getList());
//        dataTable.setRecordsTotal((int) pageInfo.getTotal());
//        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());
//
        //返回数据到页面
        String jsonString = JSON.toJSONString(dataTable);

        return jsonString;
    }

    //主题查询
    @RequestMapping("/issueList")
    @ResponseBody
    public String getIssueList(HttpSession session,String keys, Integer pageNo, Integer pageSize, String begin, String end,Integer status)
    {
        Admin admin = (Admin) session.getAttribute("admin");
        if( admin==null )
        {
            return null;
        }
        return JSON.toJSONString( userService.getUserFileList(keys,pageNo,pageSize,begin,end,status) );
    }

    //变更状态
    @RequestMapping("/issue.do")
    @ResponseBody
    public String userFileOperate(HttpSession session,Integer id,Integer status)
    {
        Admin admin = (Admin) session.getAttribute("admin");
        if( admin==null )
        {
            return "false";
        }
        if( userService.userFileUpdate(id,status) > 0 )
        {
            return "true";
        }
        return "false";
    }

    //删除
    @RequestMapping("/del.do")
    @ResponseBody
    public String userFileDel(HttpSession session,Integer id)
    {
        Admin admin = (Admin) session.getAttribute("admin");
        if( admin==null )
        {
            return "false";
        }
        if( userService.UserFileDel(id) > 0  )
        {
            return "true";
        }
        return "false";
    }

}
