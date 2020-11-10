package com.mms.test;

import com.mms.entity.Student;
import com.mms.mapper.StudentMapper;
import com.mms.utils.SqlSessionUntils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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
            Student student = new Student(3, "ls", 24, "s2",true);
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
            Student student = new Student(2, "ww", 25, "s1",true);
            mapper.updateStudent(student);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();


    }

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

    //使用自定义类型转换器查询
    public static void queryByConverter() {

            SqlSession sqlSession = SqlSessionUntils.getSqlSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Student student = mapper.queryByConverter(1);
            System.out.println(student);
            /*
            7、提交事务
            sqlSession.commit();*/
            //8、释放资源
            sqlSession.close();

    }

    public static void main(String[] args) {
        queryStudentById();
        /**
         * queryStudentById()方法的执行结果为
         *     Student{id=0, stuName='zs', stuAge=23, graName='s1', stuSex=true}
         * 我们发现属性名与字段名不同的属性在读取时丢失了，此处的id在数据库中为1，但是由于名称
         * 不对应导致数据丢失了，当实体类的属性名与表中的字段名不能够合理识别时（id和stuno）
         * 使用resultMap
        */

        // insertStudent();
        // queryAllStudent();
        // updateStudent();
        // deleteStudent();
        // queryByConverter();
    }

}
