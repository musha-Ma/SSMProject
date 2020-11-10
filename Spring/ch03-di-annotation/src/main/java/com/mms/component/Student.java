package com.mms.component;

import org.springframework.stereotype.Component;

/**
 * 使用注解实现自动注入
 * 1、@Component
 *    @Component 该注解的功能是使用spring容器创建对象
 *    1）、在要创建对象的类的声明上方加入该注解，该注解有一个属性value，value为spring创建的该类
 *    对象的id值
 *    2）、开发中使用将value省略，直接使用双引号将值键入即可
 *    3）、该注解使用类的无参构造创建对象
 * 2、可以实现创建对象的其他注解：
 *    @Repository 创建dao类对象，访问数据库的对象
 *    @Service    创建service类对象，业务层对象
 *    @Controller 创建控制器对象，用于分发用户的请求和显示处理结果
 *    注意：以上三个注解虽然也可以实现对象的创建，但是它们创建的对象是由特定功能的对象，
 *         不同于component注解创建的对象
 */

@Component(value = "student")
/*
    @Component(value = "student")等价于使用xml配置文件创建对象语句
    <bean id = "student" class = "com.mms.component.Student"/>
 */
public class Student {
    private String name;
    private int age;

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
