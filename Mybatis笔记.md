@[TOC](目录)
## 1、Mybatis概述
历经半个月断断续续的学习，终于是学完了mybatis框架，内心也是十分的高兴，所以写下了这篇博客分享一下学习过程中遇到的坑以及解决方法，希望可以帮助到有需要的小伙伴。下面附上本人学习mybatis的体系导图，本文的顺序也是基于此导图。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201016183150432.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
#### 1.2 传统JDBC代码分析
首先我们拿一段jdbc代码来进行分析，看看传统jdbc代码的弊端
```java
public static void main(String[] args) { 
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null; 
	try {
		//加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver"); //通过驱动管理类获取数据库链接
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8","ro ot", "root");
		//定义 sql 语句 ?表示占位符
		String sql = "select * from user where username = ?";
		//获取预处理 statement
		preparedStatement = connection.prepareStatement(sql);
		//执行sql
		preparedStatement.setString(1, "王五"); 
		resultSet = preparedStatement.executeQuery();
		//遍历查询结果集
		while(resultSet.next()){
		System.out.println(resultSet.getString("id")+" "+resultSet.getString("username"));
		}
		} catch (Exception e) { 
			e.printStackTrace()};
		}finally{ //释放资源
			try{
			if(connection != null) connection.close();
			if(preparedStatement != null) preparedStatement.close();
			if(resultSet != null) resultSet.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
```
不难发现，Connection对象、PreparedStatement对象以及ResultSet对象都是为了连接数据库、执行sql的，PreparedStatement对象给占位符赋值和使用ResultSet取值时我们还要时时关注数据类型的转换，但是从业务的角度来看，我们就是想向数据库发送一条sql语句而已，我们应该关注的不应该是sql语句就可以了吗？至于什么连接数据库、封装结果集等等繁琐步骤不应该是我们关注的。
### 1.2 ORM介绍
为了解决传统jdbc代码的弊端，orm思想就应运而生了。orm全称（Object Relational Mapping）对象关系映射。下面通过一张图进行讲解
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201016190853215.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
orm思想就是将表和对象进行映射，假设我们在数据库中存在一张person表，我们创建了一个实体类Person，此时表person就和对象person对应起来了，并且person表的字段id、name、age也和person对象的属性id、name、age映射起来了，我们对person对象的操作就是对person表的操作，orm思想能让我们像操作对象一样操作表。mybatis就是orm思想的一种具体实现，它帮我们减少了连接数据库、封装结果集对象等繁杂操作，使我们只要关注sql语句即可，类似的orm实现还有hibernate、spring data jpa等。
## 2、入门案例
mybatis提供了两种对数据库的增删改查，第一种是基于mybatis传统API的方式，另一种是动态代理方式，下面将对两种方式分别介绍。
### 2.1 环境搭建
因为mybatis是对dao的操作，所以建立一个普通的java项目即可，当然了使用maven创建也是可以的，本人是使用的普通java项目。

 1. 建立普通java项目
 2. 导入依赖，分别是jdbc驱动、mybatis.jar
 3. 编写mybatis核心配置文件config.xml
 4. 编写mapper接口及映射文件mapper.xml
 5. 编写测试类
### 2.2 基于传统方式
基于传统方式的crud是使用mybatis官方提供的API，主要是sqlsession.xxx(String statement,Object obj)方法实现crud。
##### 核心配置文件config.xml
```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--
        Mybatis主配置文件
        通过environments的default属性值与environment的id值来指定环境
    -->
    <environments default="mysql">
        <environment id="mysql">
            <!--
                transactionManager为处理事务的方式
                    JDBC：使用传统JDBC方式处理事务，即手动commit、rollback、close等
                    MANAGED：交由第三方组件处理事务，例如Spring，但是注意必须手动close
            -->
            <transactionManager type="JDBC"></transactionManager>
            <!--
                dataSource为数据源
                    POOLED：使用数据源
                    UNPOOLED：不使用数据源
                    JNDI：使用tomcat内置的数据源
            -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
                <property name="username" value="root"/>
                <property name="password" value="333"/>
            </dataSource>
        </environment>
    </environments>

    <!--加载映射配置文件-->
    <mappers>
        <mapper resource="com/mms/entity/studentMapper.xml"></mapper>
    </mappers>
</configuration>
```
##### 映射文件studentMapper.xml
```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mms.mapper.studentMapper">

    <!--
        通过namespace的值与sql标签的id值唯一确定一条sql语句
    -->
    <select id="queryStudentById" resultType="com.mms.entity.Student"  parameterType="int">
		select * from student where  stuno = #{stuNo}
	</select>
<mapper/>
```
##### 实体类
```java
//实体类
public class Student {
    //属性
    private int stuNo;
    private String stuName;
    private int stuAge;
    private String graName;

    public Student() {

    }

    public Student(int stuNo, String stuName, int stuAge, String graName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.graName = graName;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    @Override
    public String toString() {
        return this.stuNo+","+this.stuName+","+this.stuAge+","+this.graName;
    }
}
```
##### 测试
```java
package com.mms.test;

import com.mms.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Mybatis基础方式的增删改查
 * 1、实现：
 *      使用Mybatis提供的API，即SqlSession.xxx(String statement,Object obj)方法进行CRUD
 *      statement参数="namespace.sql标签的id值"即sql语句，obj为sql语句的占位符
 */
public class MybatisTest {

    //根据学号查询
    public static void queryStudentById() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、根据namespace和sql标签id获得sql语句
            String statement = "com.mms.mapper.studentMapper.queryStudentById";
            Student student = (Student)sqlSession.selectOne(statement, 1);
            System.out.println(student);
            //释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
   
    public static void main(String[] args) {
        queryStudentById();
    }

```
### 基于动态代理方式
##### 项目结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017110325350.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
mybatis提供了另一种方式来实现crud，使用sqlsession动态代理。
##### 接口类
```java
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
}
```
##### 动态代理方式详解
动态代理方式遵循约定由于配置的原则，由传统方式的crud我们可以发现程序每次都要确定statement参数，且在执行完sql语句时还要进行类型转换。动态代理方式就避免了以上两种繁琐操作，一下通过图解来解释
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017111245523.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
通过studentMapper.xml的namespace的值等于StudentMapper的全类名，select标签的id值queryStudentById等于方法名queryStudentById，parameterType的类型等于方法输入参数的类型，resultType的类型等于方法的返回值就将方法与一条sql语句唯一对应起来了，也就避免了去频繁的编写String statement = xxx;这行代码了。我们在调用queryStudentById该方法时，mybatis底层就会自动去执行相对应的sql语句了，实现了面向接口编程。
##### 测试
```java
package com.mms.test;

import com.mms.entity.Student;
import com.mms.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

//测试类
public class MybatisTest {

    //根据学号查询学生
    public static void queryStudentById() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Student student = mapper.queryStudentById(1);
            System.out.println(student);
            //释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        queryStudentById();
    }
}
```
## 3、CRUD
动态代理方式的crud，核心配置文件和mapper接口前面均已给出，下面就不再赘述了，直接给出相应的接口方法和映射sql以及测试片段
###### 3.1 增加
接口方法
```java
/**
     * 增加学生
     * @param student
     */
    void insertStudent(Student student);
```
映射sql
```java
<!--增加-->
    <insert id="insertStudent" parameterType="com.mms.entity.Student">
		insert into student (stuno,stuname,stuage,graname) values (#{stuNo},#{stuName},#{stuAge},#{graName})
	</insert>
```
测试
```java
//增加用户
    public static void insertStudent() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Student student = new Student(2, "ls", 24, "s2");
            mapper.insertStudent(student);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
###### 3.2 删除
接口方法
```java
/**
     * 根据学号删除学生
     * @param stuNo
     */
    void deleteStudent(int stuNo);
```
映射sql
```java
<!--删除-->
    <delete id="deleteStudent" parameterType="int">
		delete from student where stuno = #{stuNo}
	</delete>
```
测试
```java
public static void deleteStudent() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            mapper.deleteStudent(2);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
###### 3.3 修改
接口方法
```java
/**
     * 修改学生信息
     * @param student
     */
    void updateStudent(Student student);
```
映射sql
```java
<!--修改-->
    <update id="updateStudent" parameterType="com.mms.entity.Student">
		update student set stuname = #{stuName}, stuage = #{stuAge}, graname = #{graName} where stuno = #{stuNo}
	</update>
```
测试
```java
//根据学号修改信息
    public static void updateStudent() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Student student = new Student(2, "ww", 25, "s1");
            mapper.updateStudent(student);
            //7、提交事务
            sqlSession.commit();
            //8、释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
```
###### 3.4 查
接口方法
```java
/**
     * 查询全部
     * @return
     */
    List<Student> queryAllStudent();
```
映射sql
```java
<select id="queryAllStudent" resultType="com.mms.entity.Student">
		select * from student
	</select>
```
测试
```java
//查询全部
    public static void queryAllStudent() {
        try {
            //1、读取配置文件
            Reader reader = Resources.getResourceAsReader("config.xml");
            //2、创建SqlSessionFactoryBuilder建造者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、使用SqlSessionFactoryBuilder建造SqlSessionFactory工厂
            SqlSessionFactory build = builder.build(reader);
            //4、使用SqlSessionFactory工厂生产SqlSession对象，SqlSession就相当与JDBC的Connection对象，它包含了一系列对数据库的操作
            SqlSession sqlSession = build.openSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            List<Student> students = mapper.queryAllStudent();
            System.out.println(students);
            //释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
## 4、输入、输出参数深入
#### 4.1 输入参数
##### 4.1.1 输入参数详解
```java
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
```
##### 4.1.2 映射sql
```java
<!--测试#{}、${value}-->
	<!--输入参数为简单类型-->
	<select id="queryStudentByAge" parameterType="int" resultType="Student">

		<!--select * from student where stuage = #{stuAge}-->
		<!--
		一下写法报错，简单类型${}中的值必须为value
		select * from student where stuage = ${stuAge}-->

		select * from student where stuage = ${value}
	</select>

	<!--输入参数为string-->
	<select id="queryByString" parameterType="String" resultType="Student">
		<!--自动加单引号，成功执行-->
		select * from student where stuname = #{stuName}
		<!--原样输出，查询失败，列类型不一致
		select * from student where stuname = ${value}-->
	</select>

	<!--模糊查询-->
	<select id="queryByLike" parameterType="String" resultType="Student">
		<!--要求传递进来的值手动加上%%
		select * from student where stuname like #{stuName}-->

		select * from student where stuname like '${value}';

	</select>

	<!--嵌套查询-->
	<select id="queryByAddress" parameterType="Student" resultType="Student">
		select * from student where homeaddress = #{address.homeAddress} or schooladdress = '${address.schoolAddress}'
	</select>

	<!--
		传入参数为HashMap
			实现原理：用HashMap的key匹配占位符的值，例如key为"stuAge"，占位符为#{stuAge}
			则匹配成功，然后与key的value作为占位符的值
	-->
	<select id="queryByHashMap" parameterType="HashMap" resultType="Student">
		select * from student where stuname = #{stuName} or stuage = ${stuAge}
	</select>
```
## 5、程序优化
##### 5.1 引入SqlSession帮助类
```java
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
```
##### 5.2 引入数据库连接信息配置文件db.propertity
```java
//配置文件采用键值对的形式，根据key取value
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis
username=root
password=333
```
### 5.3 配置别名
```java
<!--
        配置别名
            1、在不配置别名的情况下，每次的resultType和parameterType的值必须要写实体类的全类名
               显得非常麻烦，设置了别名以后，在使用实体类的时候直接使用别名即可
            2、配置别名分为单个设置别名和批量设置别名
            3、别名的设置在typeAliases标签内部设置别名
    -->

    <!--
    -->
    <typeAliases>
        <!--
            设置单个别名，设置Student实体类的别名为student，以后的student就代表了Student实体类
            并且不区分别名的大小写
        -->
        <typeAlias type="com.mms.entity.Student" alias="student"/>

        <!--
            批量设置别名，会自动的将该包下的所有类定义了别名，别名就是其自身且不区分大小
        -->
        <package name="com.mms.entity" />
    </typeAliases>
```
##### 5.4 自定义类型转换器
```java
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
```
##### 测试
```java
//使用自定义类型转换器查询
    public static void queryByConverter() {

            SqlSession sqlSession = SqlSessionUntils.getSqlSession();
            //5、使用sqlSession得到接口的动态代理对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            //6、执行接口中的方法
            Student student = mapper.queryByConverter(1);
            System.out.println(student);
            /*
            7、提交事务
            sqlSession.commit();*/
            //8、释放资源
            sqlSession.close();

    }
```
##### 5.5 解决实体类属性名与表字段名不一致方案
我们在开发中一般建议实体类属性名与表字段名保持一致，如若不然将在查询时不对应字段的值会丢失（置为0），下面的注释给出了解决方案
```java
<!--解决属性名与字段名不能合理识别方案
	<select id="queryStudentById" resultMap="studentMapper"  parameterType="int">
		select * from student where  stuno = #{stuNo}
	</select>
	<resultMap id="studentMapper" type="com.mms.entity.Student">
		配置属性与字段一一对应关系，分为主键（id标签）和非主键（result）
		<id property="id" column="stuno"/>
		<result property="stuName" column="stuname"/>
		<result property="stuAge" column="stuage"/>
		<result property="graName" column="graname"/>
		需要进行类型转换的标签内部加入jdbcType和javaType
		<result property="stuSex" column="stusex" javaType="Boolean" jdbcType="INTEGER"/>
	</resultMap>-->
```
## 6、动态sql
##### if、where标签
```java
<!--
		动态sql
			通过下面的一条查询语句感受一下：通过名字和学号查询学生
			select stuno, stuanem, stuage, graname from student
			where stuno = #{stuNo} and stuname = #{stuName}

			有时我们传入的学生可能stuNo属性为空，那么此时的sql语句按理说应该变为下面的形式
			select stuno, stuanem, stuage, graname from student
			where  stuname = #{stuName}
			但是当stuNo属性不为空的时候我们又要将sql语句改回去，有没有一种方法可以动态的实现这种需求的？
			答案是使用动态sql

	<select id="dynamicSql" parameterType="Student" resultType="Student">
		select stuno, stuname, stuage, graname from student
		where stuno = #{stuNo} and stuname = #{stuName}
	</select>
	-->

	<!--动态sql实现上述需求方式1
	<select id="dynamicSql" parameterType="Student" resultType="Student">
		select stuno, stuname, stuage,graname from student where 1=1

		stuNo属性不为空且不等于0，注意写属性名而不是字段名，与sql语句相关的写字段名
		<if test="stuNo != null and stuNo != 0">
			and stuno = #{stuNo}
		</if>
		<if test="stuName != null and stuName != ''">
			and stuname = #{stuName}
		</if>
	</select>-->

	<!--动态sql实现上述需求方式2-->
	<select id="dynamicSql" parameterType="Student" resultType="Student">
		select stuno, stuname, stuage,graname from student
		<where>
			<!--
				执行sql时where标签会被mybatis底层自动解释为where关键字，即等同于：
					select stuno, stuanem, stuage, graname from student
					where stuno = #{stuNo} and stuname = #{stuName}
					且where标签会智能的帮我们处理第一个and，当它发现sql语句需要and时会自动加上and，
					不需要时会自动去掉and（即使在mapper.xml中写了and）
			-->
			<if test="stuNo != null and stuNo != 0">
				and stuno = #{stuNo}
			</if>
			<if test="stuName != null and stuName != ''">
				and stuname = #{stuName}
			</if>
		</where>

	</select>
```
##### foreach标签
```java
<!--
		foreach标签：循坏遍历
		案例：student表中有两条记录，学号分别为1，2，我们查询学号为1和2的学生记录即
		select stuno, stuname, stuage, graname from student
		where stuno in(1,2)
	-->
	<!--
		遍历对象属性：将学号放进一个类的属性里面进行遍历取出

	-->
	<select id="foreachWithFiled" parameterType="StuNo" resultType="Student">
		select stuno, stuname, stuage, graname from student
		<where>
			<!--
				foreach标签属性介绍：
				1、collection：要遍历的集合
							  规定：
								a、若集合为某个对象的属性，则collection属性值就是该对象的类名
								b、若集合为简单类型数组，collection的属性值为array
								c、若集合为对象数组，collection的属性值为object
								d、若集合为集合，collection的属性值为list
				2、open：遍历点左边的sql语句
				3、close：遍历点右边的sql语句
				4、item：存放每次遍历结果的变量，类似for(String str : strs)的str变量
				5、separator：每个遍历结果之间的分隔符
			-->
			<if test="stuNos != null and stuNos.size > 0">
				<foreach collection="stuNos" open="and stuno in(" close=")" item="stuNo" separator=",">
					#{stuNo}
				</foreach>
			</if>
		</where>
	</select>

	<!--foreach遍历集合-->
	<select id="foreachWithCollection" parameterType="list" resultType="Student">
		select * from student
		<where>
			<if test="list != null and list.size > 0">
				<foreach collection="list" open="and stuno in(" close=")" item="stuNo" separator=",">
					#{stuNo}
				</foreach>
			</if>
		</where>
	</select>

	<!--foreach遍历数组-->
	<select id="foreachWithArray" parameterType="int[]" resultType="Student">
		select * from student
		<include refid="foreachWithArray"></include>
	</select>

	<!--foreach遍历对象数组，注意parameterType、item、#{Student.stuNo}三处-->
	<select id="foreachWithObjectArray" parameterType="object[]" resultType="Student">
		select * from student
		<where>
			<if test="array != null and array.length > 0">
				<foreach collection="array" open="and stuno in(" close=")" item="Student" separator=",">
					#{Student.stuNo}
				</foreach>
			</if>
		</where>
	</select>
```

## 7、关联查询
##### 7.1 一对一关联查询
案例：每个学生有姓名、班级、年龄等信息；每个学生有学生证且学生证和学生是一一对应的，即学生和他的学生证是一对一的关系。我们创建两张表学生表和学生证表，并且让学生表的cardid字段作为外键引用学生证表的id字段，这样就将两张表关联起来了。
###### 7.1.1 学生实体类
```java
package com.mms.entity;

/**
 * 实现一对一方式二：使用resultMap
 *  1、我们之前的业务扩展类的基本思想是创建了一个子类分别继承Student类的属性和StuentCard类的属性，
 *     在逻辑上StudentAndCard类分别对应一对一查到的10个属性，因此使用resultMap大致也是这种思想
 *  2、我们只需要将属性少的类作为属性多的类的一个属性（成员变量）即可
 */
public class Student {
    //学生类属性
    private int stuNo;
    private String stuName;
    private int stuAge;
    private String graName;
    private int CID;
    private int classNo;
    //学生证属性
    private StudentCard studentCard;
    
    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    //一对一toString()
    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                ", graName='" + graName + '\'' +
                ", CID=" + CID +
                ", classNo=" + classNo +
                '}'+"--------"+this.studentCard.toString();
    }
}
```
###### 7.1.2 学生证实体类
```java
package com.mms.entity;

//学生证
public class StudentCard {
    private int cardID;
    private String cardInfo;

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getStuInfo() {
        return cardInfo;
    }

    public void setStuInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    @Override
    public String toString() {
        return "StudentCard{" +
                "cardID=" + cardID +
                ", cardInfo='" + cardInfo + '\'' +
                '}';
    }
}
```
###### 7.1.3 接口方法
```java
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
```
###### 7.1.4 映射sql
```java
<!--一对一关联查询方式一：使用业务扩展类-->
    <select id="queryOneToOne" parameterType="int" resultType="StudentAndCard">
        select s.*,c.* from student s inner join studentcard c
        on s.cid = c.cardid
        where stuno = #{stuNo}
    </select>

    <!--一对一关联查询方式一：使用resultMap-->
    <select id="OneToOneWithResultMap" parameterType="int" resultMap="studentMapping">
        select s.*,c.*
        from student s
        join studentcard c
        on s.cid = c.cardid
            where stuno = #{stuNo}
    </select>
    <!--配置-resultMap-->
    <resultMap id="studentMapping" type="Student">
        <!--学生属性对应关系-->
        <id property="stuNo" column="stuno"></id>
        <id property="stuName" column="stuname"></id>
        <id property="stuAge" column="stuage"></id>
        <id property="graName" column="graname"></id>
        <id property="CID" column="cid"></id>
        <id property="classNo" column="classno"></id>

        <!--
            学生证属性对应关系，一对一查询使用association，
            property指定学生类的哪个成员（属性名）
            id标签与result标签指定对应关系
        -->
        <association property="studentCard" javaType="StudentCard">
            <id property="cardID" column="cardid"></id>
            <result property="cardInfo" column="cardinfo"></result>
        </association>
    </resultMap>
```
###### 7.1.5 测试
```java
	//一对一：使用业务扩展类
	public static void queryOneToOne() {
	        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
	        //5、使用sqlSession得到接口的动态代理对象
	        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
	        //6、执行接口中的方法
	        StudentAndCard studentAndCard = mapper.queryOneToOne(1);
	        System.out.println(studentAndCard);
	        sqlSession.close();
	    }

	//一对一：使用resultMap
    public static void OneToOneWithResultMap() {
        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
        //5、使用sqlSession得到接口的动态代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //6、执行接口中的方法
        List<Student> students = mapper.OneToOneWithResultMap(1);
        System.out.println(students);
        sqlSession.close();
    }
```
##### 7.2 一对多关联查询
案例：一个班级有班级编号、班级名称；一个班级同时有多个学生，每个学生唯一属于一个班级，所以班级和学生就是一个一对多的关系。根据班级编号可以查询出这个班级的所以学生。
###### 7.2.1 班级实体类
```java
package com.mms.entity;

import java.util.List;

//班级类
public class StudentClass {
    //班级属性
    private int classId;
    private String classInfo;

    /**
     * 一对多与一对一不同的是，一对一将属性少的类作为属性多的类的一个属性（成员变量）即可，而一对多
     * 则恰好相反，因为一个班级有好多学生
     */
    private List<Student> studentList;//通过该成员属性让班级和学生建立起联系
    public StudentClass() {
    }

    public StudentClass(int classId, String classInfo) {
        this.classId = classId;
        this.classInfo = classInfo;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "classId=" + classId +
                ", classInfo='" + classInfo + '\'' +
                '}'+"--------"+this.studentList.toString();
    }
}
```
###### 7.2.2 接口方法
```java
/**
     * 一对多
     * @param classId
     * @return
     */
    List<StudentClass> oneToMany(int classId);
```
###### 7.2.3 映射sql
```java
<!--一对多-->
    <select id="oneToMany" parameterType="int" resultMap="oneToManyMapping">
        select s.*, c.*
        from student s
        inner join class c
        on s.classno = c.classid
        where classid = #{classId}
    </select>
    <resultMap id="oneToManyMapping" type="StudentClass">
        <id property="classId" column="classid"></id>
        <result property="classInfo" column="classinfo"></result>

        <!--指定Student属性的对应关系，一对多使用collection，注意ofType-->
        <collection property="studentList" ofType="Student">
            <id property="stuNo" column="stuno"></id>
            <result property="stuName" column="stuname"></result>
            <result property="stuAge" column="stuage"></result>
            <result property="graName" column="graname"></result>
            <result property="CID" column="cid"></result>
            <result property="classNo" column="classno"></result>
        </collection>

    </resultMap>
```
###### 7.2.4 测试
```java
	public static void oneToMany() {
	        SqlSession sqlSession = SqlSessionUntils.getSqlSession();
	        //5、使用sqlSession得到接口的动态代理对象
	        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
	        //6、执行接口中的方法
	        List<StudentClass> studentClasses = mapper.oneToMany(1);
	        System.out.println(studentClasses);
	        sqlSession.close();
	    }
```
## 8、延迟加载
```java
<!--一对一关联查询使用延迟加载-->
    <select id="OneToOneWithLazyLoading" resultMap="oneToOnWithLazyLoading">
        select * from student
    </select>
    <!--配置-resultMap-->
    <resultMap id="oneToOnWithLazyLoading" type="Student">

        <!--先根据学号查询学生-->
        <id property="stuNo" column="stuno"></id>
        <result property="stuName" column="stuname"></result>
        <result property="cardID" column="cardid"></result>
        <result property="classID" column="classid"></result>

        <!--
            1、延迟加载：根据需要查询学生证信息，将查询学生证的sql写入另一个mapper.xml文件中，并在
            association标签的select属性上引用即可在需要时启动加载
            2、属性介绍
                select：属性值为需要延迟加载的sql语句，具体为namespace.sql标签id值
                column：表与表之间的连接字段，即外键
        -->
        <association property="studentCard" javaType="StudentCard" select="com.mms.mapper.StudentCardMapper.queryByCardID" column="cardid"></association>
    </resultMap>

    <!--根据班级编号延时加载学生信息-->
    <select id="queryByClassNo" resultType="Student" parameterType="int">
        select * from student where classid = #{classNo}
    </select>
```
## 9、缓存
##### 9.1 缓存介绍以及接口方法
```java
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

```
###### 运行结果分析
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017120947851.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017121006516.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
## 10、逆向工程
```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
  PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
  "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
   <context id="DB2Tables" targetRuntime="MyBatis3">
   <commentGenerator>
   <!--
			suppressAllComments属性值：
				true:自动生成实体类、SQL映射文件时没有注释
				false:自动生成实体类、SQL映射文件，并附有注释
		  -->
  <property name="suppressAllComments" value="true" />
 </commentGenerator>
 
 
 <!-- 数据库连接信息 -->
  <jdbcConnection driverClass="com.mysql.jdbc.Driver"
   connectionURL="jdbc:mysql://localhost:3306/test?characterEncoding=utf8&amp;useSSL=true&amp;serverTimezone=UTC"
   userId="root"  password="333">
  </jdbcConnection>
  <!-- 
			forceBigDecimals属性值： 
				true:把数据表中的DECIMAL和NUMERIC类型，
解析为JAVA代码中的java.math.BigDecimal类型 
				false(默认):把数据表中的DECIMAL和NUMERIC类型，
解析为解析为JAVA代码中的Integer类型 
		-->
 <javaTypeResolver>
  	<property name="forceBigDecimals" value="false" />
 </javaTypeResolver>
 <!-- 
		targetProject属性值:实体类的生成位置  
		targetPackage属性值：实体类所在包的路径
	-->
 <javaModelGenerator targetPackage="com.mms.entity"
                            targetProject="./src">
  <!-- trimStrings属性值：
			true：对数据库的查询结果进行trim操作
			false(默认)：不进行trim操作       
		  -->
  <property name="trimStrings" value="true" />
 </javaModelGenerator>
 <!-- 
		targetProject属性值:SQL映射文件的生成位置  
		targetPackage属性值：SQL映射文件所在包的路径
	-->
  <sqlMapGenerator targetPackage="com.mms.mapper"
			targetProject="./src">
  </sqlMapGenerator>
  <!-- 生成动态代理的接口  -->
 <javaClientGenerator type="XMLMAPPER" targetPackage="com.mms.mapper" targetProject="./src">
 </javaClientGenerator>
 
 <!-- 指定数据库表  -->
  <table tableName="Student"> </table>
  <table tableName="studentCard"> </table>
  <table tableName="studentClass"> </table>
 </context>
</generatorConfiguration>
```