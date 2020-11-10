package com.mms.value;

public class Address {

    private String homeAddress;
    private String schoolAddress;

    //有参构造
    public Address(String homeAddress, String schoolAddress) {
        this.homeAddress = homeAddress;
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
