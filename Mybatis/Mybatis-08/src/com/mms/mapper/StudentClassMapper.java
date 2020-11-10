package com.mms.mapper;

import com.mms.entity.StudentClass;

import java.util.List;

public interface StudentClassMapper {

    /**
     * 一对多关联查询，使用延时加载
     * 查询班级的学生
     * @return
     */
    List<StudentClass> findAllStudentWithClass();
}
