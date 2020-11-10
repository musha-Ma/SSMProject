package com.mms.entity;

// 实体类（学生表）
public class Student {
    private Integer stuNo;
    private String stuName;

    public Student() {
    }

    public Student(Integer stuNo, String stuName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
    }

    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                '}';
    }
}
