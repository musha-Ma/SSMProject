package com.mms.value;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

//测试类
public class MyTest {

    //构造注入参数名
    @Test
    public void test01() {
        String config = "ba02/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Student student = (Student) ac.getBean("student");
        System.out.println(student);
    }

    //构造注入下标
    @Test
    public void test02() {
        String config = "ba02/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Student student = (Student) ac.getBean("diByContructor");
        System.out.println(student);
    }

    //构造注入创建File对象
    @Test
    public void test03() {
        String config = "ba02/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        File file = (File) ac.getBean("myFile");
        System.out.println(file.getName());
    }
}
