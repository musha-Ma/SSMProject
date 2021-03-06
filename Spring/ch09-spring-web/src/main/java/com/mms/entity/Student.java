package com.mms.entity;
//实体类
public class Student {

    private int stuNo;
    private String stuName;
    private int cardID;
    private int classID;

    public Student() {
    }

    public Student(int stuNo, String stuName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
    }

    public Student(int stuNo, String stuName, int cardID, int classID) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.cardID = cardID;
        this.classID = classID;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", cardID=" + cardID +
                ", classID=" + classID +
                '}';
    }
}
