package com.jd.cardapp.dao;

import com.jd.cardapp.model.Card;
import com.jd.cardapp.model.CardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardMapper {
    int countByExample(CardExample example);

    int deleteByExample(CardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Card record);

    int insertSelective(Card record);

    List<Card> selectByExample(CardExample example);

    Card selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Card record, @Param("example") CardExample example);

    int updateByExample(@Param("record") Card record, @Param("example") CardExample example);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);

    List<Card> getCardList(@Param("key") String key[], @Param("begin") String begin, @Param("end") String end,@Param("status") Integer status);

    List<Card> getCards(@Param("key") String key[] ,@Param("belong") String belong );
}