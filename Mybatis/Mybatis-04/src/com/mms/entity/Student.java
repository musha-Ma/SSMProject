package com.mms.entity;

//实体类
public class Student {
    private int stuNo;
    private String stuName;
    private int stuAge;
    private String graName;
    private boolean sex;
    private Address address;

    public Student() {}

    public Student(int stuNo, String stuName, int stuAge, String graName, boolean sex, Address address) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.graName = graName;
        this.sex = sex;
        this.address = address;
    }

    public Student(int stuNo, String stuName, int stuAge, String graName, boolean sex) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.graName = graName;
        this.sex = sex;
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

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                ", graName='" + graName + '\'' +
                ", sex=" + sex +
                ", address=" + address +
                '}';
    }
}
