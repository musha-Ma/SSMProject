package com.mms.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("student")
public class Student {

    @Value("王五")
    private String name;
    @Value("25")
    private int age;

    /**
     * 使用jdk自带的@Resource注解实现引用类型属性自动注入
     * 1、该注解默认是使用byName方式实现注入，若是byName方式注入失败，则再使用byType方式注入
     */
    @Resource
    private School school;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}
