package com.mms;

import com.mms.entity.Student;
import com.mms.mapper.StudentMapper;
import com.mms.service.IStudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    //测试对象是否被容器创建
    @Test
    public void test01() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        String[] beanNames = ac.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("容器中对象的名称--->"+beanName+"--->"+ac.getBean(beanName));
        }
    }

    //执行查询全部，不使用service
    @Test
    public void test02() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获取mapper的代理对象
        StudentMapper mapper = (StudentMapper) ac.getBean("studentMapper");
        List<Student> students = mapper.queryAll();
        for (Student student : students) {
            System.out.println("student--->"+student);
        }
    }

    //执行增加学生，使用service
    @Test
    public void test03() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获取service对象
        IStudentService service = (IStudentService) ac.getBean("studentServiceImpl");
        Student student = new Student();
        student.setStuName("呵呵");
        student.setStuNo(1111);
        student.setCardID(1115);
        student.setClassID(1);
        service.addStudent(student);

    }
}
