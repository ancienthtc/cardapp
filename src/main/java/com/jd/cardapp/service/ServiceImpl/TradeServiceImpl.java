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
import com.jd.cardapp.util.StringUtil.GenerateString;
import com.jd.cardapp.util.date.DateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
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

    @Resource
    private GraphicMapper graphicMapper;

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
    public PageInfo<Withdraw> withdrawList(String keys,Integer pageNo, Integer pageSize, String begin, String end) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        String[] key=keys.split("\\s+");
        List<Withdraw> withdrawList = withdrawMapper.getWithdrawList(key,begin,end);
        PageInfo<Withdraw> result = new PageInfo<>(withdrawList);
        return result;
    }

    @Override
    public Buy checkBuy(Integer uid, Integer cid) { //todo: 待修改
        BuyExample buyExample = new BuyExample();
        BuyExample.Criteria criteria = buyExample.createCriteria();
        criteria.andCardEqualTo(cid);
        criteria.andUserEqualTo(uid);

        //
        criteria.andTypeEqualTo(0); //新!

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

                    //todo:待修改
                    buy.setDetail(JSON.toJSONString(card,filter));
                    buy.setType(0); //新!
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


    @Override
    public Recharge searchRecharge(Integer uid, Double price) {
        //查找之前未付款的订单
        RechargeExample rechargeExample = new RechargeExample();
        RechargeExample.Criteria criteria = rechargeExample.createCriteria();
        criteria.andAmountEqualTo(price);
        criteria.andUserEqualTo(uid);
        rechargeExample.setOrderByClause("paytime");
        List<Recharge> rechargeList = rechargeMapper.selectByExample(rechargeExample);
        if(rechargeList!=null)
        {
            return rechargeList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public Recharge createOrder(Recharge recharge,Integer uid, Double price) {
        if( recharge.getId()!=null && recharge.getAmount()==price && recharge.getUser()==uid)
        {
            return recharge;
        }
        try {
            recharge = new Recharge();
            recharge.setAmount(price);
            String sequence = new GenerateString().getOrderid();
            recharge.setSequence(sequence);
            recharge.setState(0);
            recharge.setUser(uid);
            recharge.setAmount(price);
            rechargeMapper.insertSelective(recharge);
            System.out.println( recharge.getId() );

        }catch (Exception e)
        {
            return null;
        }

        return recharge;
    }

    @Override
    @Transactional
    public int RechargeUpdate(String sequence, Integer status,Timestamp paytime) {
//        RechargeExample rechargeExample = new RechargeExample();
//        RechargeExample.Criteria criteria = rechargeExample.createCriteria();
//        criteria.andSequenceEqualTo(sequence);
        int i = rechargeMapper.orderUpdate(sequence,paytime,status);

        Recharge recharge = rechargeMapper.selectBySequence(sequence);
        User user = userMapper.selectByPrimaryKey(recharge.getId());
        user.setBalance( user.getBalance() + recharge.getAmount() );
        int j = userMapper.updateByPrimaryKey(user);
        return i+j;
    }

    @Override
    public Buy checkReport(Integer uid, Integer gid) {
        BuyExample buyExample = new BuyExample();
        BuyExample.Criteria criteria = buyExample.createCriteria();
        criteria.andGraphicEqualTo(gid);
        criteria.andUserEqualTo(uid);

        //
        criteria.andTypeEqualTo(1); //新!

        List<Buy> buys = buyMapper.selectByExample(buyExample);
        if(buys.size()>0)
        {
            return buys.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public Map<String, Object> userBuyReport(Integer uid, Integer gid) {
        Map<String,Object> m = new HashMap<>();

        try {
            User user = userMapper.selectByPrimaryKey(uid);
            Graphic graphic = graphicMapper.selectByPrimaryKey(gid);
            if(graphic.getPay()>user.getBalance())
            {
                m.put("status",1);
                m.put("msg","余额不足");
                throw new Exception();
            }
            else
            {
                //用户扣除余额
                user.setBalance( user.getBalance()-graphic.getPay() );

                Buy buy = new Buy();
                //复制目标
                String sourceFolder = graphic.getFolder();
                String sourceFile = graphic.getFilename();
                String targetPath = config.getUpload() + config.getUserfolder() + uid + "//";
                String targetFile = graphic.getTitle()+sourceFile.substring(sourceFile.lastIndexOf("."));
                if( imageService.fileCopy(sourceFolder,sourceFile,targetPath,targetFile) )
                {
                    buy.setDetail( JSON.toJSONString(graphic) );
                    buy.setType(1); //新!
                    buy.setGraphic(gid);
                    buy.setUser(uid);
                    buy.setPay(graphic.getPay());
                    buy.setCreatetime(DateExample.getNowTimeByDate());
                    buy.setFolder1(targetPath);
                    buy.setPic1(targetFile);
                    buy.setState(0);
                    int i=buyMapper.insertSelective(buy);

                    //用户更新
                    user.setUpdatetime(DateExample.getNowTimeByDate());
                    int j = userMapper.updateByPrimaryKeySelective(user);

                    //更新下载次数
                    graphic.setDownload( graphic.getDownload()+1 );
                    int g = graphicMapper.updateByPrimaryKeySelective(graphic);


                    if( i>0 && j>0 && g>0)
                    {
                        m.put("status",0);
                        m.put("msg","购买成功");
                        return m;
                    }
                    else
                    {
                        m.put("status",3);
                        m.put("msg","部分更新失败");
                        throw new Exception();
                    }
                }
                else
                {
                    m.put("status",2);
                    m.put("msg","文件复制失败");
                    throw new Exception();
                }

            }


        }catch (Exception e)
        {
            e.printStackTrace();
            return m;
        }
        //m.put("status",3);
        //m.put("msg","购买失败");
        //return m;
    }

    @Override
    @Transactional
    public int WithdrawAdd(Withdraw withdraw) {
        //state 提现状态  0-等待提现 1-提现完成 2-审核未通过
        //applytime
        try
        {
            if(withdraw.getAccount()==null || withdraw.getAname()==null || withdraw.getUser()==null)
            {
                throw new Exception();
            }
            //判断当前用户余额
            User user = userMapper.selectByPrimaryKey(withdraw.getUser());
            if( withdraw.getAmount() > user.getBalance() )
            {
                throw new Exception();
            }

            //更新账户
            user.setBalance( user.getBalance() - withdraw.getAmount() );
            //user.setUpdatetime(DateExample.getNowTimeByDate());
            int i = userMapper.updateByPrimaryKeySelective(user);

            //提现表
            withdraw.setState(0);
            withdraw.setApplytime(DateExample.getNowTimeByDate());
            int j = withdrawMapper.insertSelective(withdraw);
            if( i < 1 || j < 1 )
            {
                throw new Exception();
            }
            return j;
        }
        catch (Exception e)
        {
            return 0;
        }

    }

    @Override
    @Transactional
    public int WithdrawSure(Integer wid) {
        Withdraw withdraw = withdrawMapper.selectByPrimaryKey(wid);
        withdraw.setState(1);
        withdraw.setFinish(DateExample.getNowTimeByDate());
        return withdrawMapper.updateByPrimaryKeySelective(withdraw);
    }

    @Override
    @Transactional
    public int WithdrawRefuse(Integer wid) {
        Withdraw withdraw = withdrawMapper.selectByPrimaryKey(wid);
        try {
            if( withdraw==null )
            {
                throw new Exception();
            }
            //恢复数值
            User user = userMapper.selectByPrimaryKey(withdraw.getUser());
            user.setBalance( user.getBalance() + withdraw.getAmount() );
            int i = userMapper.updateByPrimaryKeySelective(user);

            withdraw.setState(2);
            int j = withdrawMapper.updateByPrimaryKeySelective(withdraw);
            if( i+j <= 1 )
            {
                throw new Exception();
            }
            return j;

        }catch (Exception e)
        {
            return 0;
        }
    }

    @Override
    public int WithdrawDel(Integer wid) {
        Withdraw withdraw = withdrawMapper.selectByPrimaryKey(wid);
        if( withdraw.getState()==2 )
        {
            return withdrawMapper.deleteByPrimaryKey(wid);
        }
        return 0;
    }

}
