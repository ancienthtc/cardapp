package com.jd.cardapp.service;

import com.github.pagehelper.PageInfo;
import com.jd.cardapp.model.Buy;
import com.jd.cardapp.model.Recharge;
import com.jd.cardapp.model.Withdraw;

import java.sql.Timestamp;
import java.util.Map;

public interface TradeService {

    //新充值记录
    PageInfo<Recharge> rechargeList(Integer pageNo,Integer pageSize,String begin,String end);

    //新申请提现
    PageInfo<Withdraw> withdrawList(String keys,Integer pageNo,Integer pageSize,String begin,String end);

    //查询用户是否购买名片
    Buy checkBuy(Integer uid,Integer cid);

    Map<String,Object> userBuyCard(Integer uid,Integer cid);

    Recharge searchRecharge(Integer uid,Double price);

    Recharge createOrder(Recharge recharge,Integer uid,Double price);

    int RechargeUpdate(String sequence,Integer status,Timestamp paytime);

    //检查用户是否购买
    Buy checkReport(Integer uid,Integer gid);

    Map<String,Object> userBuyReport(Integer uid,Integer gid);

    //申请提现
    int WithdrawAdd(Withdraw withdraw);

    //确认提现
    int WithdrawSure(Integer wid);

    //拒绝提现
    int WithdrawRefuse(Integer wid);

    //提现记录删除
    int WithdrawDel(Integer wid);


}
