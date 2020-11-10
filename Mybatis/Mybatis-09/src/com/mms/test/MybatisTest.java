package com.mms.test;

import com.mms.entity.Student;
import com.mms.mapper.StudentMapper;
import com.mms.utils.SqlSessionUntils;
import org.apache.ibatis.session.SqlSession;

//测试类
public class MybatisTest {

    //一级缓存测试
    public static void cacheOne() {
        //使用同一个SqlSession对象进行查询操作
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //mapper1和mapper2都是由同一个SqlSession对象产生的
        StudentMapper mapper1 = sqlSession.getMapper(StudentMapper.class);
        StudentMapper mapper2 = sqlSession.getMapper(StudentMapper.class);
        Student student1 = mapper1.queryByStuNo(110);
        //清空SqlSession缓存
        sqlSession.commit();
        Student student2 = mapper1.queryByStuNo(110);
        //验证
        System.out.println(student1.getStuNo()+","+student1.getStuName());
        System.out.println("=====================");
        System.out.println(student2.getStuNo()+","+student2.getStuName());
        sqlSession.close();
    }

    //二级缓存测试
    public static void cacheTwo() {
        //使用同一个SqlSession对象进行查询操作
        SqlSession sqlSession1 = SqlSessionUntils.getSqlSession();
        //mapper1和mapper2都是由不同SqlSession对象产生的，但是由于它们是由同一个namespace产生的，所以共享二级缓存
        StudentMapper mapper1 = sqlSession1.getMapper(StudentMapper.class);
        Student student1 = mapper1.queryByStuNo(110);
        //触发二级缓存
        sqlSession1.close();
        SqlSession sqlSession2 = SqlSessionUntils.getSqlSession();
        StudentMapper mapper2 = sqlSession2.getMapper(StudentMapper.class);
        Student student2 = mapper2.queryByStuNo(110);
        //验证
        // System.out.println(sqlSession1==sqlSession2);
        System.out.println(student1.getStuNo()+","+student1.getStuName());
        System.out.println("=====================");
        System.out.println(student2.getStuNo()+","+student2.getStuName());
        sqlSession2.close();
    }

    public static void main(String[] args) {
        // cacheOne();
        cacheTwo();
    }

}
