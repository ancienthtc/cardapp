package com.jd.cardapp;

import com.alibaba.fastjson.JSON;
import com.jd.cardapp.config.MyCustomConfig;
import com.jd.cardapp.model.Card;
import com.jd.cardapp.service.CardService;
import com.jd.cardapp.service.LoginService;
import com.jd.cardapp.service.TradeService;
import com.jd.cardapp.service.UserService;
import com.jd.cardapp.util.api.message.SmsVariableDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardAppApplicationTests {

	@Autowired
	private MyCustomConfig myCustomConfig;

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	@Autowired
	private TradeService tradeService;

	@Autowired
	private CardService cardService;

	@Test
	public void contextLoads() {

		System.out.println( myCustomConfig.getUpload() );

	}

	@Test
	public void test1()
	{
		JSON.toJSONString( loginService.admin_login("super","123") );
	}

	@Test
	public void test2()
	{
		String s=JSON.toJSONString( userService.getUserList("A B",1,10,"2018-03-13","2018-03-14") );
		System.out.println(s);
	}

	@Test
	public void test3()
	{
		System.out.println( JSON.toJSON( tradeService.rechargeList(1,10,null,null) ) );
	}

	@Test
	public void test4()
	{
		SmsVariableDemo demo = new SmsVariableDemo();
		boolean b=demo.SendMessage("13771973232","2467","5");
		System.out.println(b);
	}

	@Test
	public void test5()
	{
		Card card = cardService.getDetail(1);
		System.out.println( JSON.toJSON(card) );
	}

	@Test
	public void test6()
	{

	}

}
