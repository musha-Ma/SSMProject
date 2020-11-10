package com.mms.test;

import com.mms.entity.Student;
import com.mms.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

//测试类
public class MybatisTest {

    //查询全部
    public static void queryStudentById() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Student student = mapper.queryStudentById(1);
            System.out.println(student);
            //释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //增加用户
    public static void insertStudent() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Student student = new Student(2, "ls", 24, "s2");
            mapper.insertStudent(student);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查询全部
    public static void queryAllStudent() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            List<Student> students = mapper.queryAllStudent();
            System.out.println(students);
            //释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据学号修改信息
    public static void updateStudent() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Student student = new Student(2, "ww", 25, "s1");
            mapper.updateStudent(student);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteStudent() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            mapper.deleteStudent(2);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // queryStudentById();
        // insertStudent();
        // queryAllStudent();
        // updateStudent();
        deleteStudent();
    }
}
