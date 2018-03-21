package com.jd.cardapp.dao;

import com.jd.cardapp.model.Buy;
import com.jd.cardapp.model.BuyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyMapper {
    int countByExample(BuyExample example);

    int deleteByExample(BuyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Buy record);

    int insertSelective(Buy record);

    List<Buy> selectByExample(BuyExample example);

    Buy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Buy record, @Param("example") BuyExample example);

    int updateByExample(@Param("record") Buy record, @Param("example") BuyExample example);

    int updateByPrimaryKeySelective(Buy record);

    int updateByPrimaryKey(Buy record);
}