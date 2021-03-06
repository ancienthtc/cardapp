package com.jd.cardapp.dao;

import com.jd.cardapp.model.Withdraw;
import com.jd.cardapp.model.WithdrawExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WithdrawMapper {
    int countByExample(WithdrawExample example);

    int deleteByExample(WithdrawExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Withdraw record);

    int insertSelective(Withdraw record);

    List<Withdraw> selectByExample(WithdrawExample example);

    Withdraw selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Withdraw record, @Param("example") WithdrawExample example);

    int updateByExample(@Param("record") Withdraw record, @Param("example") WithdrawExample example);

    int updateByPrimaryKeySelective(Withdraw record);

    int updateByPrimaryKey(Withdraw record);

    List<Withdraw> getWithdrawList(@Param("key") String key[],@Param("begin") String begin, @Param("end") String end);
}