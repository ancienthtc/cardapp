package com.jd.cardapp.dao;

import com.jd.cardapp.model.Graphic;
import com.jd.cardapp.model.GraphicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GraphicMapper {
    int countByExample(GraphicExample example);

    int deleteByExample(GraphicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Graphic record);

    int insertSelective(Graphic record);

    List<Graphic> selectByExample(GraphicExample example);

    Graphic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Graphic record, @Param("example") GraphicExample example);

    int updateByExample(@Param("record") Graphic record, @Param("example") GraphicExample example);

    int updateByPrimaryKeySelective(Graphic record);

    int updateByPrimaryKey(Graphic record);
}