package com.mms.test;

import com.mms.entity.Student;
import com.mms.entity.StudentClass;
import com.mms.mapper.StudentClassMapper;
import com.mms.mapper.StudentMapper;
import com.mms.utils.SqlSessionUntils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

//测试类
public class MybatisTest {

    /**
     * 根据学号延时加载学生证信息
     */
    public static void oneToOneLazy() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.OneToOneWithLazyLoading();
        for (Student student : students) {
            System.out.println(student.getStuName()+","+student.getStuNo());
            System.out.println("以下是延时加载的学生证信息");
            System.out.println(student.getStudentCard().getCardNo()+","+student.getStudentCard().getCardInfo());
        }
        sqlSession.close();
    }

    //根据班级编号延时加载班级学生
    public static void oneToManyLazy() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        StudentClassMapper mapper = sqlSession.getMapper(StudentClassMapper.class);
        List<StudentClass> allStudentWithClass = mapper.findAllStudentWithClass();
        for (StudentClass studentClass : allStudentWithClass) {
            System.out.println(studentClass.getClassNo()+","+studentClass.getClassInfo());
            System.out.println("以下是延时加载的学生信息");
            List<Student> studentList = studentClass.getStudentList();
            for (Student student : studentList) {
                System.out.println(student.getStuName()+","+student.getClassID());
            }
        }
        sqlSession.close();

    }

    public static void main(String[] args) {
        // oneToOneLazy();
        oneToManyLazy();
    }

}
