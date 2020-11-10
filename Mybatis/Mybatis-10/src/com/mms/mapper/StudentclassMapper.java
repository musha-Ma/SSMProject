package com.mms.mapper;

import com.mms.entity.Studentclass;
import com.mms.entity.StudentclassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentclassMapper {
    long countByExample(StudentclassExample example);

    int deleteByExample(StudentclassExample example);

    int deleteByPrimaryKey(Integer classno);

    int insert(Studentclass record);

    int insertSelective(Studentclass record);

    List<Studentclass> selectByExample(StudentclassExample example);

    Studentclass selectByPrimaryKey(Integer classno);

    int updateByExampleSelective(@Param("record") Studentclass record, @Param("example") StudentclassExample example);

    int updateByExample(@Param("record") Studentclass record, @Param("example") StudentclassExample example);

    int updateByPrimaryKeySelective(Studentclass record);

    int updateByPrimaryKey(Studentclass record);
}