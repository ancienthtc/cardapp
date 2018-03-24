package com.jd.cardapp.dao;

import com.jd.cardapp.model.Recharge;
import com.jd.cardapp.model.RechargeExample;

import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeMapper {
    int countByExample(RechargeExample example);

    int deleteByExample(RechargeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Recharge record);

    int insertSelective(Recharge record);

    List<Recharge> selectByExample(RechargeExample example);

    Recharge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Recharge record, @Param("example") RechargeExample example);

    int updateByExample(@Param("record") Recharge record, @Param("example") RechargeExample example);

    int updateByPrimaryKeySelective(Recharge record);

    int updateByPrimaryKey(Recharge record);

    List<Recharge> getRechargeList(@Param("begin") String begin, @Param("end") String end);

    int orderUpdate(@Param("sequence") String sequence, @Param("paytime") Timestamp paytime, @Param("status") Integer status);

    Recharge selectBySequence(@Param("sequence") String sequence);
}