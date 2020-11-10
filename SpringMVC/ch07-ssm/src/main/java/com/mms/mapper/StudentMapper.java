package com.mms.mapper;

import com.mms.entity.Student;

import java.util.List;

// mapper接口
public interface StudentMapper {

    /**
     * 查询全部
     * @return
     */
    List<Student> findAll();

    /**
     * 增加学生
     * @param student
     * @return
     */
    int addStudent(Student student);
}
