package com.mms.component;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试类
public class Test01 {

    //使用@Component创建对象
    @Test
    public void test01() {
        String config = "component/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Student student = (Student) ac.getBean("student");
        System.out.println("student="+student);
    }
}
