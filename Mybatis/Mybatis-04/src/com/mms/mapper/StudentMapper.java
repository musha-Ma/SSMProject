package com.mms.mapper;

import com.mms.entity.Address;
import com.mms.entity.Student;

import java.util.List;
import java.util.Map;

/**
 * 关于Mybatis输入参数的深入探究
 * 一、输入参数
 *   1、#{}和${}的不同
 *          当输入参数为简单类型（8个基本类型+String）时：
 *              #{任意值}（但是一般建议与表字段相同），${value}（必须为value，否则报错）
 *          当输入参数为对象类型时：
 *              #{对象的属性名}，${对象的属性名}
 *      2） 当parameterType为String类型时：
 *              #{}占位符最后被解析为sql语句时会自动带上单引号''，称为自动类型转换
 *              ${}占位符最后被解析为sql语句时会原样输出（适用于动态排序）
 *              例如：
 *                   slect * from student where stuname = #{stuName}等价于
 *                   slect * from student where stuname = 'stuName'可以成功执行
 *                   但是：
 *                   slect * from student where stuname = ${stuName}等价于
 *                   slect * from student where stuname = stuName 查询失败，字段类型与字段值不符合
 *                   要想避免这种情况可以使用手动给${}加单引号，即如下
 *                   slect * from student where stuname = '${stuName}'
 *
 *                   动态排序举例（此处的stuname为String类型）：
 *                   select * from student order by stuname = #{stuName} desc等价于
 *                   select * from student order by stuname = 'stuName' desc排序失败，因为此时的'stuName'为常量而不是字段名
 *                   这种情况就可以使用${}来实现动态排序
 *      3）#{}可以防止sql注入，${}不能
 *      4）关于模糊查询使用
 *          select * from student where stuname like stuname = #{stuName}
 *              要求传递进来的String类型的形式为"%xxx%"，因为之前说过了#{}为String时会自动加上单引号
 *
 *          select * from student where stuname like stuname = '%${stuName}%'等价于
 *          select * from student where stuname like stuname = '%stuName%'
 *
 *  2、#{}和${}的相同
 *      1）都可以获取对象的值（嵌套类型对象）
 *      2）实现模糊查询
 */
public interface StudentMapper {

    /**
     * 查询全部
     * @return
     */
    List<Student> queryAllStudent();

    /**
     * 根据学号查询学生
     * @return
     */
    Student queryStudentById(int stuNo);

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

    /**
     * 使用#{}、${}
     * @param stuAge
     * @return
     */
    List<Student> queryStudentByAge(int stuAge);

    /**
     * 使用#{}、${}
     * @param stuName
     * @return
     */
    List<Student> queryByString(String stuName);


    List<Student> queryByLike(String stuName);

    /**
     * 嵌套查询
     * @param student
     * @return
     */
    List<Student> queryByAddress(Student student);

    /**
     * 传入参数为HashMap
     * @param map
     * @return
     */
    List<Student> queryByHashMap(Map<String,Object> map);
}
