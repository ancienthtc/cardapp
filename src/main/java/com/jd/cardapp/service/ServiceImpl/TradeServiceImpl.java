package com.jd.cardapp.service.ServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.cardapp.config.MyCustomConfig;
import com.jd.cardapp.dao.*;
import com.jd.cardapp.model.*;
import com.jd.cardapp.service.ImageService;
import com.jd.cardapp.service.TradeService;
import com.jd.cardapp.util.date.DateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradeServiceImpl implements TradeService {

    @Resource
    private RechargeMapper rechargeMapper;

    @Resource
    private WithdrawMapper withdrawMapper;

    @Resource
    private BuyMapper buyMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CardMapper cardMapper;

    @Resource
    private IncomeMapper incomeMapper;

    @Autowired
    private ImageService imageService;

    @Autowired
    private MyCustomConfig config;

    private ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if(v==null)
                return "";
            return v;
        }
    };

    //新充值记录
    @Override
    public PageInfo<Recharge> rechargeList(Integer pageNo, Integer pageSize, String begin, String end) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);

        List<Recharge> rechargeList = rechargeMapper.getRechargeList(begin,end);
        PageInfo<Recharge> result = new PageInfo<>(rechargeList);
        return result;
    }

    //新申请提现
    @Override
    public PageInfo<Withdraw> withdrawList(Integer pageNo, Integer pageSize, String begin, String end) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Withdraw> withdrawList = withdrawMapper.getWithdrawList(begin,end);
        PageInfo<Withdraw> result = new PageInfo<>(withdrawList);
        return result;
    }

    @Override
    public Buy checkBuy(Integer uid, Integer cid) {
        BuyExample buyExample = new BuyExample();
        BuyExample.Criteria criteria = buyExample.createCriteria();
        criteria.andCardEqualTo(cid);
        criteria.andUserEqualTo(uid);
        List<Buy> buys = buyMapper.selectByExample(buyExample);
        if(buys.size()>0)
        {
            return buys.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public Map<String, Object> userBuyCard(Integer uid, Integer cid) {
        Map<String,Object> m = new HashMap<>();
        try {
            User user = userMapper.selectByPrimaryKey(uid);
            Card card = cardMapper.selectByPrimaryKey(cid);
            if( card.getBuy()==0 )
            {
                m.put("status",1);
                m.put("msg","免费无需购买");
                throw new Exception();
            }
            if(card.getBuy()>user.getBalance())
            {
                m.put("status",2);
                m.put("msg","余额不足");
                throw new Exception();
            }
            else
            {
                //收益
                Income income = new Income();

                user.setBalance( user.getBalance()-card.getBuy() );
                Buy buy = new Buy();
                String target = config.getUpload() + config.getUserfolder() + uid + "//";
                String sourceFile1 = card.getPic1();
                String sourceFile2 = card.getPic2();
                String targetFile1="",targetFile2="";
                if(card.getFolder1()!=null && card.getPic1()!=null)
                {
                    targetFile1 = "user"+uid+sourceFile1.substring(sourceFile1.lastIndexOf("."));
                }
                if(card.getFolder2()!=null && card.getPic2()!=null)
                {
                    targetFile2 = "user"+uid+sourceFile2.substring(sourceFile2.lastIndexOf("."));
                }

                boolean b1=false,b2=false;
                //图片复制
                if( card.getFolder1()!=null && card.getPic1()!=null )
                {
                    b1=imageService.fileCopy(card.getFolder1(),sourceFile1,target,targetFile1);
                }
                if( card.getFolder2()!=null && card.getPic2()!=null )
                {
                    b2=imageService.fileCopy(card.getFolder2(),sourceFile2,target,targetFile2);
                }
                if( b1||b2 )
                {
                    User addAccount = userMapper.selectByPrimaryKey( card.getUser() );
                    if( addAccount != null )
                    {
                        addAccount.setBalance( addAccount.getBalance() + income.getIncome());
                        addAccount.setUpdatetime(DateExample.getNowTimeByDate());
                        userMapper.updateByPrimaryKeySelective(addAccount);
                    }


                    buy.setDetail(JSON.toJSONString(card,filter));
                    buy.setCard(cid);
                    buy.setUser(uid);
                    buy.setPay(card.getBuy());
                    buy.setCreatetime(DateExample.getNowTimeByDate());
                    buy.setFolder1(target);
                    buy.setFolder2(target);
                    if(card.getFolder1()!=null && card.getPic1()!=null)
                    {
                        buy.setPic1(targetFile1);
                    }
                    if(card.getFolder2()!=null && card.getPic2()!=null)
                    {
                        buy.setPic2(targetFile2);
                    }
                    buy.setState(0);
                    int i=buyMapper.insertSelective(buy);

                    income.setTime(DateExample.getNowTimeByDate());
                    income.setAll(card.getBuy());
                    income.setDivide(card.getBuy()*0.4);
                    income.setIncome(card.getBuy()*0.6);
                    income.setUser(addAccount.getId());
                    income.setResource("card");
                    income.setTarget(cid);
                    int j = incomeMapper.insertSelective(income);

                    user.setUpdatetime(DateExample.getNowTimeByDate());
                    userMapper.updateByPrimaryKeySelective(user);

                    //更新card购买次数
                    card.setBuytimes( card.getBuytimes() + 1 );
                    cardMapper.updateByPrimaryKeySelective(card);

                    if( i>0 && j>0)
                    {
                        m.put("status",0);
                        m.put("msg","购买成功");
                        return m;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return m;
        }

        m.put("status",3);
        m.put("msg","购买失败");
        return m;
    }




}
