package com.mms.value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 使用注解实现自动注入：
 * 1、简单类型注入：@Value
 *    直接在简单类型属性声明语句的上面加入注解@Value即可，并在@Value的括号内键入属性值
 *    注意不论简单类型属性的数据类型，均由双引号将属性值括起来
 * 2、引用类型注入：@Autowired
 *    1）@Autowired是spring提供的属性赋值，用于给引用类型赋值，有byName和byType两种
 *      方式，默认使用byType方式自动注入
 *    2）若是要强制至于byName方式，要在@Autowired注解下面加入
 *      @Qualifier(value = "bean的id")注解，，若程序在给引用类型注入时在xml文件中找不到
 *      该id的bean标签或者手找不到该id的@Component注解，则报错；若不想让程序在赋值失败时报
 *      错，可以在@Autowired注解的required属性值置为false
 */

@Component("student")
public class Student {

    @Value("张三")
    private String name;
    @Value("23")
    private int age;

    /*引用类型注入（byType方式）
    @Autowired
    private School school;*/

    //引用类型赋值（byName方式）
    @Autowired(required = false)
    @Qualifier(value = "mySchool")
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
