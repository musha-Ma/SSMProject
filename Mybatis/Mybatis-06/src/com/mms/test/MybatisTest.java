package com.mms.test;

import com.mms.entity.StuNo;
import com.mms.entity.Student;
import com.mms.mapper.StudentMapper;
import com.mms.utils.SqlSessionUntils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

//测试类
public class MybatisTest {

    //动态sql（if、where标签）
    public static void dynamicSql() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        Student student = new Student();
        // student.setStuNo(2);
        student.setStuName("lili");
        List<Student> students = mapper.dynamicSql(student);
        System.out.println(students);
        sqlSession.close();
    }

    //foreach标签遍历对象属性
    public static void queryByFiled() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<Integer> stuNos = new ArrayList<>();
        stuNos.add(1);
        stuNos.add(2);
        StuNo stuNo = new StuNo(stuNos);
        List<Student> students = mapper.foreachWithFiled(stuNo);
        System.out.println(students);
        //释放资源
        sqlSession.close();
    }

    //foreach标签遍历集合
    public static void foreachWithCollection() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<Student> students = mapper.foreachWithCollection(list);
        System.out.println(students);
        sqlSession.close();
    }

    public static void foreachWithArray() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        int[] num = {1,2};
        List<Student> students = mapper.foreachWithArray(num);
        System.out.println(students);
        sqlSession.close();
    }

    public static void foreachWithObjectArray() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        Student stu1 = new Student();
        Student stu2 = new Student();
        stu1.setStuNo(1);
        stu2.setStuNo(2);
        Student[] students = {stu1,stu2};
        List<Student> students1 = mapper.foreachWithObjectArray(students);
        System.out.println(students1);
        sqlSession.close();
    }

    public static void main(String[] args) {
        // dynamicSql();
        // queryByFiled();
        // foreachWithCollection();
        // foreachWithArray();
        foreachWithObjectArray();
    }

}
