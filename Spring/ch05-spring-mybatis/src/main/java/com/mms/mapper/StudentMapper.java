package com.mms.mapper;

import com.mms.entity.Student;

import java.util.List;

//dao接口
public interface StudentMapper {
    //查询全部
    List<Student> queryAll();
    //新增学生
    void addStudent(Student student);
}
