package com.mms.entity;

import java.util.List;

//班级表
public class StudentClass {
    //班级表字段
    private int classNo;
    private String classInfo;

    //从表学生，一个班级有多个学生（一对多）
    private List<Student> studentList;

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "classNo=" + classNo +
                ", classInfo='" + classInfo + '\'' +
                '}';
    }
}
