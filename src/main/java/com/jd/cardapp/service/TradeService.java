package com.jd.cardapp.service;

import com.github.pagehelper.PageInfo;
import com.jd.cardapp.model.Buy;
import com.jd.cardapp.model.Recharge;
import com.jd.cardapp.model.Withdraw;

import java.util.Map;

public interface TradeService {

    //新充值记录
    PageInfo<Recharge> rechargeList(Integer pageNo,Integer pageSize,String begin,String end);

    //新申请提现
    PageInfo<Withdraw> withdrawList(Integer pageNo,Integer pageSize,String begin,String end);

    //查询用户是否购买名片
    Buy checkBuy(Integer uid,Integer cid);

    Map<String,Object> userBuyCard(Integer uid,Integer cid);
}
