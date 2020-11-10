package com.mms.mapper;

import com.mms.entity.Student;

/**
 * 关于Mybatis中的一级缓存和二级缓存
 * 一、一级缓存
 * 1、什么是一级缓存？
 *      前提是同一个SqlSession对象，当我们用同一个SqlSession对象去查询相同的数据时，只有在第一次查
 *      询时会向数据库发送sql语句并将查询到的结果存入SqlSession中（作为缓存），当第N次使用相同的
 *      SqlSession对象去查询相同的数据时不再向数据库发送sql语句，直接从SqlSession缓存中拿取对象即可
 * 2、Mybatis默认自动开启一级缓存
 * 3、一级缓存的生命周期？
 *      当我们使用SqlSession对象执行了commit()方法后会清空相关数据，再使用该SqlSession对象去查询
 *      相同数据时不会从SqlSession缓存中拿取（因为已经被清空），而是向数据库发送sql语句进行查询并将
 *      结果保存到SqlSession缓存中，即只有在第一次查询时会向数据库发送sql语句
 *
 * 二：二级缓存
 * 1、什么是二级缓存？
 *      a、由同一个namespace生成的Mapper对象，即sqlSession.getMapper(xxx.class)中的xxx是
 *         同一个接口，这些相同接口的代理对象共享二级缓存；如果有多个xxMapper.xml的namespace值相同，
 *         则通过这些xxxMapper.xml产生的xxMapper对象 仍然共享二级缓存。
 *      b、二级缓存是将查询到的数据保存在硬盘当中，当同一个namespace对象产生的代理对象执行相同的sql时
 *         从硬盘当中直接拿取数据，也是在第一次发送sql语句。
 *      c、二级缓存的失效也是执行commit()方法、触发将对象写入二级缓存的时机：
 *         SqlSession对象的close()方法。
 *      d、实现二级缓存的类必须实现序列化接口
 * 2、二级缓存与一级缓存的区别？
 *      分类：
 *      a、若由同一个sqlsession对象产生的mapper代理类（namespace不同），则这些mapper对象共享一级缓存
 *      b、若由不同的sqlsession对象产生的mapper代理类（namespace不同），则这些mapper对象不共享缓存
 *      c、若由不同的sqlsession对象产生的mapper代理类（namespace相同），则这些mapper对象共享二级缓存
 *      d、若由相同的sqlsession对象产生的mapper代理类（namespace相同同），则这些mapper对象同时共享一、二级缓存
 */
public interface StudentMapper {

    /**
     * 根据学号查询学生
     * @param stuNo
     * @return
     */
    Student queryByStuNo(int stuNo);
}
