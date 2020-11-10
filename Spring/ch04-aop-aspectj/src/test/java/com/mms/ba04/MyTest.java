package com.mms.ba04;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试
public class MyTest {

    //aop环绕通知
    @Test
    public void test01() {
        String config = "ba04/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //使用cglib动态代理
        IService service = (IService) ac.getBean("service");
        //代理对象的类型=com.mms.ba04.ServiceImpl$$EnhancerBySpringCGLIB$$22646088
        System.out.println("代理对象的类型="+service.getClass().getName());

        Student student = new Student();
        student.setName("李四");
        student.setAge(24);
        service.doStudent(student);
    }

}
