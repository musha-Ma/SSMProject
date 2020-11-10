package com.mms.test;

import com.mms.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Mybatis基础方式的增删改查
 * 1、实现：
 *      使用Mybatis提供的API，即SqlSession.xxx(String statement,Object obj)方法进行CRUD
 *      statement参数="namespace.sql标签的id值"即sql语句，obj为sql语句的占位符
 */
public class MybatisTest {

    //根据学号查询
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
            //5、根据namespace和sql标签id获得sql语句
            String statement = "com.mms.mapper.studentMapper.queryStudentById";
            Student student = (Student)sqlSession.selectOne(statement, 1);
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
            String statement = "com.mms.mapper.studentMapper.insertStudent";
            Student student = new Student(4, "ww", 24, "s3");
            sqlSession.insert(statement, student);
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
            String statement = "com.mms.mapper.studentMapper.queryAllStudent";
            List<Student> students = sqlSession.selectList(statement);
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
            String statement = "com.mms.mapper.studentMapper.updateStudent";
            Student student = new Student(4, "zl", 26, "s2");
            sqlSession.update(statement,student);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //删除学生
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
            String statement = "com.mms.mapper.studentMapper.deleteStudent";
            sqlSession.delete(statement,4);
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
        queryAllStudent();
        // updateStudent();
        // deleteStudent();
    }
}
