package com.mms.component;

public class Address {

    private String homeAddress;
    private String schoolAddress;

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
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
