package com.jd.cardapp.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.cardapp.config.MyCustomConfig;
import com.jd.cardapp.dao.CardMapper;
import com.jd.cardapp.model.Card;
import com.jd.cardapp.model.CardExample;
import com.jd.cardapp.service.CardService;
import com.jd.cardapp.service.ImageService;
import com.jd.cardapp.util.StringUtil.GenerateString;
import com.jd.cardapp.util.date.DateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Resource
    private CardMapper cardMapper;

    @Autowired
    private MyCustomConfig customConfig;

    @Autowired
    private ImageService imageService;

    @Override
    public PageInfo<Card> getCards(String type, Integer pageNo, String keys) {
        pageNo = pageNo == null?1:pageNo;
        String[] key=keys.split("\\s+");

        PageHelper.startPage(pageNo, 10);

        List<Card> cardList = cardMapper.getCards(key,type);
        PageInfo<Card> result = new PageInfo<>(cardList);
        return result;
    }

    @Override
    public PageInfo<Card> getCardsList(String keys, Integer pageNo, Integer pageSize, String begin, String end,Integer status) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        String[] key=keys.split("\\s+");
        if(status<0 || status>2)
        {
            status=0;
        }
        List<Card> cardList = cardMapper.getCardList(key,begin,end,status);
        PageInfo<Card> result = new PageInfo<>(cardList);
        return result;
    }

    @Override
    public int CardAdd(Card card, MultipartFile file1, MultipartFile file2) {
        String filename1 = new GenerateString().getFileName("card")+getOriginal(file1);
        String filename2 = new GenerateString().getFileName("card")+getOriginal(file2);
        String path = customConfig.getUpload() + customConfig.getCardfolder();
        boolean b1,b2;
        if( b1 = imageService.picAdd(path,filename1,file1) )
        {
            card.setPic1(filename1);
            card.setFolder1(path);
        }
        if( b2 = imageService.picAdd(path,filename2,file2) )
        {
            card.setPic2(filename1);
            card.setFolder2(path);
        }
        if( b1 || b2 )
        {
            card.setCreatetime(DateExample.getNowTimeByDate());
            return cardMapper.insertSelective(card);
        }
        return 0;
    }

    @Override
    public int CardCheck(Integer status, Integer id) {
        if ( !(status==0 || status==1 || status==2) )
        {
            return 0;
        }
        Card card = cardMapper.selectByPrimaryKey(id);
        if(card!=null)
        {
            if(card.getReview() != status )
            {
                card.setReview( status );
                card.setUpdatetime(DateExample.getNowTimeByDate());
                return cardMapper.updateByPrimaryKeySelective(card);
            }
        }
        return 0;
    }

    @Override
    public Card getDetail(Integer id) {
        Card card = cardMapper.selectByPrimaryKey(id);
        return card;
    }

    @Override
    public int CardDel(Integer id) {
        return 0;
    }

    @Override
    public PageInfo<Card> myCards(Integer uid, Integer pageNo , Integer pageSize) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria();
        criteria.andUserEqualTo(uid);
        cardExample.setOrderByClause("createtime desc");
        List<Card> cardList = cardMapper.selectByExample(cardExample);
        PageInfo<Card> result = new PageInfo<>(cardList);
        return result;
    }

    public String getOriginal(MultipartFile file)
    {
        return  "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }

}
