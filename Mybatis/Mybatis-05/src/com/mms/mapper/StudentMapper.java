package com.mms.mapper;

import com.mms.entity.Address;
import com.mms.entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关于Mybatis输出参数的深入探究
 * 1、输出参数为简单类型（8个基本+String）
 * 2、输出参数为对象类型，无论返回的对象为一个还是多个都只写对象全类名或者别名
 * 3、输出参数为HashMap
 *      1）使用HashMap存储对象属性
 *      2）使用resultType+HashMap解决属性名与字段名不同的情况
 *         例如：实体类的id属性与字段值stuno名称不一致，除了使用resultMap外，还可以使用resultType+HashMap解决
 *              方案：
 *                  select id "stuno" from student where id = #{任意值}
 */
public interface StudentMapper {

    /**
     * 查询记录总数
     * @return
     */
    int queryTotal();

    /**
     * 查询全部
     * @return
     */
    List<Student> queryAllStudents();

    /**
     * 错误写法，一个HashMap只能存放一个学生的信息，多个学生应该用多个HashMap对象存放
     * HashMap<String,Object> resultIsHashMap();
     */

    List<HashMap<String,Object>> resultIsHashMap();

    /**
     * 使用HashMap解决属性名与字段名不一致情况
     * @param stuNo
     * @return
     */
    List<Student> resultTypeWithHashMap(int stuNo);
}
