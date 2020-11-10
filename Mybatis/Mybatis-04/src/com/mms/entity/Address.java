package com.mms.entity;

//学生类的复合属性
public class Address {
    private String homeAddress;
    private String schoolAddress;

    public Address() {}

    public Address(String homeAddress, String schoolAddress) {
        this.homeAddress = homeAddress;
        this.schoolAddress = schoolAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "homeAddress='" + homeAddress + '\'' +
                ", schoolAddress='" + schoolAddress + '\'' +
                '}';
    }
}
