package com.mms.service.impl;

import com.mms.entity.Student;
import com.mms.mapper.StudentMapper;
import com.mms.service.IStudentService;

import java.util.List;

public class StudentServiceImpl implements IStudentService {

    //mapper属性
    private StudentMapper mapper;
    //set注入给mapper对象赋值

    public void setMapper(StudentMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Student> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public void addStudent(Student student) {
        mapper.addStudent(student);
    }
}
