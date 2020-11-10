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
    public static void queryStudentById() {
            SqlSession sqlSession = SqlSessionUntils.getSqlSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Student student = mapper.queryStudentById(1);
            System.out.println(student);
            //释放资源
            sqlSession.close();
    }

    //增加用户
    public static void insertStudent() {

            SqlSession sqlSession = SqlSessionUntils.getSqlSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Address address = new Address("xj", "cq");
            Student student = new Student(3, "ls", 24, "s2",true,address);
            mapper.insertStudent(student);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();

    }

    //查询全部
    public static void queryAllStudent() {

            SqlSession sqlSession = SqlSessionUntils.getSqlSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            List<Student> students = mapper.queryAllStudent();
            System.out.println(students);
            //释放资源
            sqlSession.close();

    }

    //根据学号修改信息
    public static void updateStudent() {

            SqlSession sqlSession = SqlSessionUntils.getSqlSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Address address = new Address("cs", "xz");
            Student student = new Student(2, "ww", 25, "s1",true,address);
            mapper.updateStudent(student);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();


    }

    //删除学生
    public static void deleteStudent() {

            SqlSession sqlSession = SqlSessionUntils.getSqlSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            mapper.deleteStudent(2);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();

    }

    //使用#{}、${}
    public static void queryStudentByAge() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<Student> students = mapper.queryStudentByAge(23);
        System.out.println(students);
        //8、释放资源
        sqlSession.close();
    }

    public static void queryByString() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<Student> students = mapper.queryByString("zs");
        System.out.println(students);
        sqlSession.close();
    }

    public static void queryByLike() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<Student> students = mapper.queryByLike("%zs%");
        System.out.println(students);
        sqlSession.close();
    }

    public static void queryByAddress() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        Student student = new Student();
        Address address = new Address("bj", "xa");
        student.setAddress(address);
        List<Student> students = mapper.queryByAddress(student);
        System.out.println(students);
        sqlSession.close();
    }

    public static void queryByHashMap() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        Map<String,Object> map = new HashMap<>();
        map.put("stuAge",23);
        map.put("stuName","lili");
        List<Student> students = mapper.queryByHashMap(map);
        System.out.println(students);
        sqlSession.close();
    }

    public static void main(String[] args) {
        // queryStudentById();
        // insertStudent();
        // queryAllStudent();
        // updateStudent();
        // deleteStudent();
        // queryByConverter();
        // queryStudentByAge();
        // queryByString();
        // queryByLike();
        // queryByAddress();
        queryByHashMap();
    }

}
