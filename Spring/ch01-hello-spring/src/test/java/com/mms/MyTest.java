package com.mms;

import com.mms.service.SomeService;
import com.mms.service.impl.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试类
public class MyTest {

    @Test
    public void test01() {
        //使用传统方式调用doSome()方法
        SomeService service = new SomeServiceImpl();
        service.doSome();
    }

    //使用spring容器创建对象
    @Test
    public void test02() {
        //1、指定spring配置文件的名称
        String config = "beans.xml";
        //2、创建表示spring容器的对象 ApplicationContext
        //ClassPathXmlApplicationContext：表示从类路径中加载spring配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //3、从容器中获取对象
        SomeService service = (SomeService)ac.getBean("someService");
        //4、调用方法
        service.doSome();
    }

    /**
     * 测试spring容器创建对象的时机
     *  在创建spring容器时，会创建配置文件中的所有对象
     */

    @Test
    public void test03() {
        //1、指定spring配置文件路径
        String config = "beans.xml";
        //2、创建spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        /**
         * 测试输出结果：
         * SomeServiceImpl类的无参构造执行了...
         * SomeServiceImpl类的无参构造执行了...
         * 验证了spring调用类的无参构造完成对象的创建
         */
    }

    //获取spring容器中java对象的信息
    @Test
    public void test04() {
        String config = "beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获取spring容器中对象的个数
        int beansCount = ac.getBeanDefinitionCount();
        System.out.println("spring容器中的对象个数="+beansCount);
        //获取spring容器中对象的名称（即bean标签的id值）
        String[] beansNames = ac.getBeanDefinitionNames();
        for (String beanName : beansNames) {
            System.out.println(beanName);
        }
    }

    //使用spring容器创建非自定义对象（例如java.utils下类的对象)
    @Test
    public void test05() {
        String config = "beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        String myString = (String)ac.getBean("myString");
        System.out.println(myString.getClass().getName());
    }

}
