package com.mms.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

//SqlSession对象帮助类
public class SqlSessionUntils {
    private static SqlSessionFactory sqlSessionFactory;

    //使用类加载单例模式
    static
    {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            sqlSessionFactory = builder.build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
