package com.mms.mapper;

import com.mms.entity.Studentcard;
import com.mms.entity.StudentcardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentcardMapper {
    long countByExample(StudentcardExample example);

    int deleteByExample(StudentcardExample example);

    int deleteByPrimaryKey(Integer cardno);

    int insert(Studentcard record);

    int insertSelective(Studentcard record);

    List<Studentcard> selectByExample(StudentcardExample example);

    Studentcard selectByPrimaryKey(Integer cardno);

    int updateByExampleSelective(@Param("record") Studentcard record, @Param("example") StudentcardExample example);

    int updateByExample(@Param("record") Studentcard record, @Param("example") StudentcardExample example);

    int updateByPrimaryKeySelective(Studentcard record);

    int updateByPrimaryKey(Studentcard record);
}