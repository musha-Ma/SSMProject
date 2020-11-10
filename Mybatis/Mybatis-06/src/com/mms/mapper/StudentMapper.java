package com.mms.mapper;

import com.mms.entity.StuNo;
import com.mms.entity.Student;

import java.util.List;

/**
 * 关于mybatis动态sql
 *  1、什么是动态sql？
 *      所谓动态sql就是可以"会动"的sql语句，使用sql标签来实现动态sql
 *  2、if标签
 */
public interface StudentMapper {

    /**
     * 动态sql
     * @param student
     * @return
     */
    List<Student> dynamicSql(Student student);

    /**
     * 将输入参数封装到对象属性中进行遍历
     * @param stuNo
     * @return
     */
    List<Student> foreachWithFiled(StuNo stuNo);

    /**
     * 将输入参数封装到集合中进行遍历
     * @param list
     * @return
     */
    List<Student> foreachWithCollection(List<Integer> list);

    /**
     * 将输入参数封装到数组中进行遍历
     * @param num
     * @return
     */
    List<Student> foreachWithArray(int[] num);

    /**
     * 将输入参数封装到对象数组中进行遍历
     * @param students
     * @return
     */
    List<Student> foreachWithObjectArray(Student[] students);
}
