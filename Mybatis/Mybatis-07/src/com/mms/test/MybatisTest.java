package com.mms.test;

import com.mms.entity.Student;
import com.mms.entity.StudentAndCard;
import com.mms.entity.StudentClass;
import com.mms.mapper.StudentMapper;
import com.mms.utils.SqlSessionUntils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

//测试类
public class MybatisTest {

    public static void queryOneToOne() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        StudentAndCard studentAndCard = mapper.queryOneToOne(1);
        System.out.println(studentAndCard);
        sqlSession.close();
    }

    public static void OneToOneWithResultMap() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<Student> students = mapper.OneToOneWithResultMap(1);
        System.out.println(students);
        sqlSession.close();
    }

    public static void oneToMany() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<StudentClass> studentClasses = mapper.oneToMany(1);
        System.out.println(studentClasses);
        sqlSession.close();
    }

    public static void main(String[] args) {
        // queryOneToOne();
        // OneToOneWithResultMap();
        oneToMany();
    }

}
