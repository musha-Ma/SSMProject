package com.mms.ba02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试
public class MyTest {

    //aop前置通知
    @Test
    public void test01() {
        String config = "ba02/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获取目标对象，此时的service就是spring生成的代理对象
        //注意返回值类型是接口类型，不能是实现类接口，否则报错
        IService service = (IService) ac.getBean("service");
        //使用代理对象执行方法
        String name = service.doOther("张三");
        System.out.println(name);
    }

    //测试后置方法是否会改变连接点方法的返回值
    @Test
    public void test02() {
        String config = "ba02/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获取目标对象，此时的service就是spring生成的代理对象
        //注意返回值类型是接口类型，不能是实现类接口，否则报错
        IService service = (IService) ac.getBean("service");
        //使用代理对象执行方法
        Student student = new Student();
        student.setAge(23);
        student.setName("张三");
        Student student1 = service.doStudent(student);
        System.out.println(student1);
    }
}
