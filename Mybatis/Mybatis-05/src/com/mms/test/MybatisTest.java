package com.mms.test;

import com.mms.entity.Address;
import com.mms.entity.Student;
import com.mms.mapper.StudentMapper;
import com.mms.utils.SqlSessionUntils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//测试类
public class MybatisTest {

    //查询全部
    public static void queryTotal() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        int count = mapper.queryTotal();
        System.out.println(count);
        //释放资源
        sqlSession.close();
    }

    public static void queryAllStudents() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<Student> students = mapper.queryAllStudents();
        System.out.println(students);
        sqlSession.close();
    }

    public static void resultIsHashMap() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<HashMap<String, Object>> studentHashMap = mapper.resultIsHashMap();
        System.out.println(studentHashMap);
        sqlSession.close();
    }

    public static void resultTypeWithHashMap() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<Student> students = mapper.resultTypeWithHashMap(1);
        System.out.println(students);
        sqlSession.close();
    }

    public static void main(String[] args) {
        // queryTotal();
        // queryAllStudents();
        // resultIsHashMap();
        resultTypeWithHashMap();
    }

}
