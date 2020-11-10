package com.mms.mapper;

import com.mms.entity.Student;

import java.util.List;

/**
 * 面向接口开发（基于动态代理方式的增删改查）
 *  1.接口的全类名=mapper映射文件的namespace属性值
 *    接口的方法名=mapper映射文件的sql标签的id值
 *    接口的方法参数类型=mapper映射文件sql标签的parameterType属性值（未配置别名的情况下mapper映射文件sql标签的parameterType与resultType的值均为全类名）
 *    接口的方法返回值=mapper映射文件sql标签的resultType属性值
 *  2.注意一点是无论mapper映射文件的sql语句的返回值是一个对象还是多个对象，resultType的值均为实体类的全类名而不是集合<实体类>的形式
 *  3.DML语句需要提交事务
 */
public interface StudentMapper {

    /**
     * 根据学号查询学生
     * @return
     */
    Student queryStudentById(int stuNo);




    /**
     * 查询全部
     * @return
     */
    List<Student> queryAllStudent();

    /**
     * 增加学生
     * @param student
     */
    void insertStudent(Student student);

    /**
     * 修改学生信息
     * @param student
     */
    void updateStudent(Student student);

    /**
     * 根据学号删除学生
     * @param stuNo
     */
    void deleteStudent(int stuNo);
}
