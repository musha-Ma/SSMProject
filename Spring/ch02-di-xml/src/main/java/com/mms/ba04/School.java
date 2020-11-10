package com.mms.ba04;

public class School {

    private String schoolName;
    private String schoolAddress;

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolName='" + schoolName + '\'' +
                ", schoolAddress='" + schoolAddress + '\'' +
                '}';
    }
}
