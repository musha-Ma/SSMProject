package com.mms.mapper;

import com.mms.entity.Student;
import com.mms.entity.StudentAndCard;
import com.mms.entity.StudentClass;

import java.util.List;

/**
 * mybatis多表查询
 * 一、一对一
 *  方式一：使用业务扩展类
 *  方式二：使用resultMap
 *
 * 二：一对多
 *  使用resultMap，思想与一对一使用resultMap类似
 */
public interface StudentMapper {

    /**
     * 一对一关联查询
     * @param stuNo
     * @return
     */
    StudentAndCard queryOneToOne(int stuNo);

    /**
     * 一对一使用resultMap
     * @param stuNo
     * @return
     */
    List<Student> OneToOneWithResultMap(int stuNo);

    /**
     * 一对多
     * @param classId
     * @return
     */
    List<StudentClass> oneToMany(int classId);
}
