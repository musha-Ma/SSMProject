package com.mms.service.impl;

import com.mms.entity.Student;
import com.mms.mapper.StudentMapper;
import com.mms.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "studentService")
public class StudentServiceImpl implements IStudentService {
    // 自动注入
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }
}
