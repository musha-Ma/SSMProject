package com.mms.converter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mybatis类型转换器
 *  1、什么是类型转换器？
 *      类型转换器是将java代码和db之间的数据进行类型转换的，试想一下java实体类中的属性存储到db中时，db
 *      是怎么识别属性的数据类型的呢？其实是类型转换器起了作用，Mybatis内置了一些类型转换器
 *  2、本案例是将Student类的stuSex属性与表student中的stusex进行转换
 *      即：boolean -> int
 *         即我们将属性值为boolean的stuSex存入到数据库中时，数据库怎么将boolean类型转换为int类型
 *         我们规定stuSex属性值为true时对应stusex字段值为1（男）
 *               stuSex属性值为false时对应stusex字段值为0（女）
 *  3、实现类型转换器步骤：
 *     a、需要实现TypeHandler接口或者间接的继承TypeHandler的实现类BaseTypeHandler
 *     b、在核心配置文件进行配置
 *  4、关于resultType和resultMap的区别及使用场景
 *      a、当实体类的属性类型与表中的字段类型能够合理识别时使用resultType，否则使用resultMap
 *      b、当实体类的属性名与表中的字段名能够合理识别时（stuno和stuNo）使用resultType，
 *         否则使用resultMap
*/
public class BooleanToIntConverter extends BaseTypeHandler<Boolean> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Boolean aBoolean, JdbcType jdbcType) throws SQLException {
        /**
         * 1、set方法即为java代码数据类型到db中的处理方法，即怎么处理java类型
         * 2、preparedStatement用于存值，
         *   resultSet用于取值
         *   i代表占位符下标
         *   aBoolean代表java类型
         *   jdbcType代表jdbc类型
         */


        if (aBoolean) {
            //如果java类型为true，根据规定true对应db字段1，设置对应字段值为1
            preparedStatement.setInt(i,1);
        } else {
            preparedStatement.setInt(i,0);
        }
    }

    @Override
    public Boolean getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //get方法代表取值，即db类型取出来以后怎么处理为boolean类型，s参数代表使用字段名取值
        int result = resultSet.getInt(s);
        /*
        if (result == 1) {
            return true;
        } else {
            return false;
        }*/
        return result == 1 ? true : false;
    }

    @Override
    public Boolean getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int result = resultSet.getInt(i);//根据列的位置取值
        return result == 1 ? true : false;
    }

    @Override
    public Boolean getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        //根据存储过程取值
        int result = callableStatement.getInt(i);
        return result == 1 ? true : false;
    }
}
