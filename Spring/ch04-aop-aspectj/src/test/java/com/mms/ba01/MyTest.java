package com.mms.ba01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试
public class MyTest {

    //aop前置通知
    @Test
    public void test01() {
        String config = "ba01/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获取目标对象，此时的service就是spring生成的代理对象
        //注意返回值类型是接口类型，不能是实现类接口，否则报错
        IService service = (IService) ac.getBean("service");
        //使用代理对象执行方法
        service.doSome("张三",23);
    }

}
