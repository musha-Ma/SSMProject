package com.mms.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mySchool")
public class School {

    //注入值
    @Value("西南大学")
    private String schoolAddress;
    @Value("新疆")
    private String homeAddress;

    @Override
    public String toString() {
        return "School{" +
                "schoolAddress='" + schoolAddress + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                '}';
    }
}
