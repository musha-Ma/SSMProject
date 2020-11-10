package com.mms.mapper;

import com.mms.entity.Student;

import java.util.List;

/**
 * 一对一，一对多延迟加载
 */
public interface StudentMapper {

    /**
     * 一对一延时加载：根据学号关联查询学生证信息
     * @return
     */
    List<Student> OneToOneWithLazyLoading();

    /**
     * 一对多延时加载
     * @param classNo
     * @return
     */
    Student queryByClassNo(int classNo);
}
