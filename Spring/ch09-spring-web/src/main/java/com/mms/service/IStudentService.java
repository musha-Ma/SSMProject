package com.mms.service;

import com.mms.entity.Student;

import java.util.List;

public interface IStudentService {

    List<Student> queryAll();
    void addStudent(Student student);
}
