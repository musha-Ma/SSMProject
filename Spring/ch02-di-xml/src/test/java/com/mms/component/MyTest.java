package com.mms.component;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试类
public class MyTest {

    //使用set注入给对象属性赋值
    @Test
    public void test01() {
        String config = "ba01/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //执行完14行此时Student对象的属性已被赋值，获取对象进行验证
        Student stu = (Student) ac.getBean("student");
        System.out.println(stu); //Student{name='张三', age=23}
    }

    //验证set注入调用的是对象的set方法
    @Test
    public void test02() {
        String config = "ba01/applicationContext.xml";

        /*
         * 此时会调用set方法进行赋值
         * setName...
         * setAge...
         */
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

    }

    //验证没有属性的setXxx方法是否报错
    @Test
    public void test03() {
        String config = "ba01/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获取对象
        Student stu = (Student) ac.getBean("student");

    }

    //验证引用类型属性
    @Test
    public void test04() {
        String config = "ba01/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Student stu = (Student) ac.getBean("student");
        System.out.println(stu);
    }
}
