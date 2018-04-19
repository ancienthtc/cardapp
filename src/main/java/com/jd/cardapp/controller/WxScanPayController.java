package com.jd.cardapp.controller;

import com.jd.cardapp.service.TradeService;
import com.jd.cardapp.service.WechatPayService;
import com.jd.cardapp.util.api.wx.GlobalConfig;
import com.jd.cardapp.util.api.wx.QRCodeUtil;
import com.jd.cardapp.util.api.wx.ResponseHandler;
import com.jd.cardapp.util.api.wx.TenpayUtil;
import com.jd.cardapp.util.date.DateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Controller
@RequestMapping("/WxPay")
@Scope("prototype")
public class WxScanPayController {

    @Autowired
    private TradeService tradeService;

    /**
     * 微信扫码付款
     * @param request
     * @param body  商品描述
     * @param orderid 订单号
     * @param price 单位:分
     * @return
     */
    @RequestMapping("/pay") //二维码支付
    public String pay(HttpServletRequest request, Map<String,Object> result, @RequestParam String body, @RequestParam String orderid, @RequestParam String price)
    {

        String currTime = TenpayUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = TenpayUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;

        String spbill_create_ip = getIpAddr(request);
        String notify_url = GlobalConfig.URL + "WxPay/result";

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", GlobalConfig.APPID);
        packageParams.put("mch_id", GlobalConfig.MCH_ID);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("out_trade_no", orderid);//订单号
        packageParams.put("total_fee", price);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", GlobalConfig.TRADE_TYPE);

        String code_url;
        WechatPayService wechatPayService= new WechatPayService();
        try
        {
            code_url = wechatPayService.getUrlCode(packageParams);

            if (code_url.equals(""))
                System.err.println(wechatPayService.getResponseMessage());

            System.out.println("code_url:"+code_url);//正式版去除

            if(code_url==null)
            {
                throw new Exception();
            }
        }catch (Exception e)
        {
            result.put("QR_URL",null);
            result.put("error",wechatPayService.getResponseMessage());

            System.out.println("error:" + wechatPayService.getResponseMessage() );
            return "front/QR";
        }
        //return code_url;
        //return "redirect:/testPay/QRcode?code_url="+code_url;
        result.put("QR_URL","/WxPay/QRcode?code_url="+code_url);
        result.put("oid",orderid);
        result.put("price",String.format("%.2f", Double.parseDouble(price)/100 ) );
        result.put("body",body);

        System.out.println("CodeURL:" + code_url );
        return "front/QR";
    }





    @RequestMapping("/QRcode")
    @ResponseBody
    public void getQrCode(String code_url, HttpServletResponse response) throws Exception {
        System.out.println("QRcode"+code_url);
        if(code_url==null || code_url=="")
        {
            return;
        }
        ServletOutputStream sos = response.getOutputStream();
        QRCodeUtil.encode(code_url, sos);
    }

    /**
     * 微信回调接口
     */
    @RequestMapping(value = "/result")
    @ResponseBody
    public void wechatOrderBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("--------result--------");
        System.out.println("ok!!!!!");
        System.out.println("--------result--------");
        // 创建支付应答对象
        ResponseHandler resHandler = new ResponseHandler(request, response);
        resHandler.setKey(GlobalConfig.KEY);
        // 判断签名是否正确
        if (resHandler.isTenpaySign()) {
            String orderid ="";
            String price="";

            String resXml = "";
            if ("SUCCESS".equals(resHandler.getParameter("result_code")))
            {
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                //获取付款信息
                orderid = resHandler.getParameter("out_trade_no") ;
                price = resHandler.getParameter("cash_fee") ;
            }
            else {
                System.out.println("支付失败,错误信息：" + resHandler.getParameter("err_code"));
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }

            System.out.println(orderid + "  " + price); //正式版去除

            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();

            //跳转--通知
            if( orderid=="" || price=="" )
            {
                //orderService.pay(null,null,1);
                System.out.println("空orderid");
            }
            else
            {
                //orderService.pay(orderid,price,0);
                System.out.println("更新余额");
                tradeService.RechargeUpdate(orderid,1, DateExample.getLocalTimeFormat());
                System.out.println("更新余额结束");
            }

        } else {
            System.out.println("通知签名验证失败");
        }
    }




    public String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }




}
