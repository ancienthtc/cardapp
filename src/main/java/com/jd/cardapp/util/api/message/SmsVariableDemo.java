package com.jd.cardapp.util.api.message;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.cardapp.common.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tianyh
 * @Description:变量短信发送
 */
public class SmsVariableDemo {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	public static final String charset = "utf-8";
	// 用户平台API账号(非登录账号,示例:N1234567)
	public static String account = "N3556817";
	// 用户平台API密码(非登录密码)
	public static String pswd = "fMpi3bqm8";

	public static void main(String[] args) throws UnsupportedEncodingException {

		//请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
		String smsVariableRequestUrl = "http://smssh1.253.com/msg/variable/json";
		//短信内容
		String msg = "【名片宝】您正在名片宝网站注册，验证码是{$var}，{$var}分钟内有效。";
		//参数组
		String params = "13771973232,123456,5;";
		//状态报告
		String report= "true";

		SmsVariableRequest smsVariableRequest=new SmsVariableRequest(account, pswd, msg, params, report);

		String requestJson = JSON.toJSONString(smsVariableRequest);

		System.out.println("before request string is: " + requestJson);

		String response = ChuangLanSmsUtil.sendSmsByPost(smsVariableRequestUrl, requestJson);

		System.out.println("response after request result is : " + response);

		SmsVariableResponse smsVariableResponse = JSON.parseObject(response, SmsVariableResponse.class);

		System.out.println("response  toString is : " + smsVariableResponse);

	}

	public boolean SendMessage(String tel,String code,String time)
	{
		//请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
		String smsVariableRequestUrl = "http://smssh1.253.com/msg/variable/json";
		//短信内容
		String msg = "【名片宝】您正在名片宝网站注册，验证码是{$var}，{$var}分钟内有效。";
		//参数组
		String params = tel+","+code+","+time+";";
		//状态报告
		String report= "true";
		SmsVariableRequest smsVariableRequest=new SmsVariableRequest(account, pswd, msg, params, report);
		String requestJson = JSON.toJSONString(smsVariableRequest);
		String response = ChuangLanSmsUtil.sendSmsByPost(smsVariableRequestUrl, requestJson);
		//System.out.println(response);
		JSONObject object = JSON.parseObject(response);
		String result = object.getString("code");
		if(result.equals("0"))
		{
			return true;
		}
		LOGGER.info(object.getString("errorMsg"));
		return false;
	}


}
/*
	result:
	{"failNum":"0","time":"20180309163103","successNum":"1","msgId":"18030916310324772","errorMsg":"","code":"0"}
 */