package com.mms.ba03;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试
public class MyTest {

    //aop环绕通知
    @Test
    public void test01() {
        String config = "ba03/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获取目标对象，此时的service就是spring生成的代理对象
        //注意返回值类型是接口类型，不能是实现类接口，否则报错
        IService service = (IService) ac.getBean("service");
        //使用代理对象执行方法
        Student student = new Student();
        student.setName("张三");
        student.setAge(23);
        Student student1 = service.doStudent(student);
        System.out.println(student1);
    }

}
