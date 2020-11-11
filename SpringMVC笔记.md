@[TOC](SpringMVC)

# 1、SpringMVC体系

经过断断续续两个多月的学习，终于是学习完了SSM框架。今天将SpringMVC的学习总结分享给各位，希望大家共勉，互相进步。还是老样子，先上思维导图，我认为学习任何东西有一个体系是很有必要的。
![springmvc导图](https://img-blog.csdnimg.cn/2020111112262125.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
# 2、SpringMVC入门案例
## 2.1 项目搭建
- 创建maven项目，推荐使用骨架创建

- 导入相关依赖，第一个入门案例我们就只需要两个依赖就可以了

  ```xml
  	<!--servlet依赖-->
      <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope>
      </dependency>
      <!--springmvc依赖-->
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.2.5.RELEASE</version>
      </dependency>
  ```


- 和传统servlet一样，我们需要在web.xml中注册servlet，只不过在springmvc中注册servlet和传统servlet有所不同，回忆一下传统servlet的执行流程
![传统servlet执行流程](https://img-blog.csdnimg.cn/20201111122747905.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
  如上图所示，超链接发出的请求some会被值为/some的url-pattern所拦截（即数字1代表的箭头），紧接着该url-pattern会根据自己的servlet-name的值MyServlet2去匹配servlet标签中的值为MyServlet2的servlet-name（箭头3），若匹配到值为MyServlet2的servlet-name标签，则将该请求交由该servlet-name标签下面的servlet-class所对应的servlet类去处理。
- 下面来看看springmvc程序是怎么执行的，下面是前台和后台的代码

  1. web.xml

     ```xml
      <!--
             注册SpringMVC中央调度器DispatcherServlet
             1、DispatcherServlet叫做中央调度器、前端控制器；
             2、DispatcherServlet在tomcat服务器启动时创建，创建DispatcherServlet的代码中有一行类似于
                new XxxController()的代码，用于在springmvc容器中创建控制器对象；注意控制器类声明语句上面
                有注解@Controller，在spring中我们已经提及过该注解，作用是创建controller对象。
             3、在创建DispatcherServlet时，要求读取springmvc配置文件，在该文件中通过组件扫描器声明了需要创建
                对象的controller类所在的包名，DispatcherServlet会将组件扫描器声明的包下带有@Controller注解
                修饰的类实例化并放入上下文对象中，需要时就可以直接使用了。这与使用spring开发web项目使用监听器初始化
                spring容器的原理是一样的。
         -->
     
         <servlet>
     
             <servlet-name>springmvc</servlet-name>
             <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
     
             <!--
                 自定义springmvc配置文件位置，默认读取位置是在WEB-INF目录下，所以需要指定位置，注意该声明
                 必须位于servlet-name声明之后
             -->
             <init-param>
                 <param-name>contextConfigLocation</param-name>
                 <param-value>classpath:springmvc.xml</param-value>
             </init-param>
     
             <!--声明在tomcat启动时优先创建DispatcherServlet对象-->
             <load-on-startup>1</load-on-startup>
         </servlet>
         <servlet-mapping>
             <servlet-name>springmvc</servlet-name>
     
             <!--
                 使用框架时，url-pattern可以使用两种方式声明
                 方式一：使用斜杠"/"，与传统servlet相同
                 方式二：使用扩展名，语法是*.Xxx、*.action、*.web等
                        例如：some.do表示拦截一切以some.do结尾的请求
                             完整url地址：localhost：8080/项目名/*.do
             -->
             <url-pattern>*.do</url-pattern>
         </servlet-mapping>
     ```

  2. springmvc.xml

     ```xml
     <!--声明组件扫描器-->
         <context:component-scan base-package="com.mms.controller"/>
     
         <!--
                 配置视图解析器
                 1、之前也看到了，每次的请求路径要写很长一大串，使用了视图解析器以后能够使请求路径更加的简洁
         -->
         <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
             <!--前缀，视图文件的路径，注意前后的两个斜杠"/"-->
             <property name="prefix" value="/WEB-INF/view/"/>
             <!--后缀，视图文件的扩展名-->
             <property name="suffix" value=".jsp"/>
         </bean>
     ```

  3. 前台jsp

     ```jsp
     <%@ page contentType="text/html;charset=UTF-8" language="java" %>
     <html>
     <head>
         <title>请求页</title>
     </head>
     <body>
         <a href="some.do">hello,SpringMVC</a>
     </body>
     </html>
     ```

     ```jsp
     <%@ page contentType="text/html;charset=UTF-8" language="java" %>
     <html>
     <head>
         <title>结果页</title>
     </head>
     <body>
         <p>欢迎使用springmvc进行开发</p>
         <!--从ModeAndView获取数据，使用el表达式-->
         <p>msg数据:${msg}</p>
         <p>function数据:${function}</p>
     </body>
     </html>
     ```

  4. 后台controller

     ```java
     /*
         控制器类：使用@Controller注解修饰，在DispacherServlet对象在读取springmvc配置文件时通过组件扫描器
                 进行实例化
      */
     @Controller
     public class MyController {
     
         /*
             @RequestMapping(value = "/Xxx")：请求映射，将一个请求与一个方法绑定起来，一个请求指定相应
                                              方法进行处理
             1、value值唯一，表示请求的路径，其中斜杠"/"代表项目根路径
             2、使用ReuestMapper修饰的方法叫做处理器方法、控制器方法，相当于传统servlet中的doGet()、doPost
             3、返回值ModeAndView：
                 Mode：数据，即处理结果
                 View：视图，例如jsp
          */
         @RequestMapping(value = "/some.do")
         public ModelAndView someDo() {
     
             //代码到这里，说明进入了someDo方法了，创建ModelAndView对象来封装结果
             ModelAndView mv = new ModelAndView();
             //添加数据，类似于request.setAttribute(key,value)
             mv.addObject("msg","第一个springmvc项目");
             mv.addObject("function","执行了someDo方法");
             //指定视图
             //mv.setViewName("/WEB-INF/view/show.jsp");
     
             //使用视图解析器
             mv.setViewName("show");
             return mv;
         }
     }
     ```

## 2.2 SpringMVC执行原理
下面通过一张图来说一下springmvc执行原理
![springmvc执行原理](https://img-blog.csdnimg.cn/20201111122851315.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
我们在上面也说过了servlet的执行原理，那么问题就来了，我们怎么让springmvc介入程序呢？也就是我们怎么指定一个请求交由springmvc来处理而不是传统servlet来处理呢?这里就要讲一下springmvc程序的入口，这个类是springmvc提供的，叫做DispacherServlet（中央调度器）。

1. 用户发出的some.do请求会被*do的url-pattern拦截；
2. 然后根据servlet-name找到DispacherServlet，中央调度器在tomcat服务器启动时进行初始化，中央调调度器在初始化会会读取springmvc配置文件，当读到注解扫描器标签时，会将注解扫描器对应包下下的带有@Controller注解的类加入springmvc容器管理；
3. 最后中央调度器会拿着用户的请求去匹配控制器方法的@RequestMapping注解的value属性值，若匹配成功则进入相应的控制器方法执行（这里的控制器方法类似于传统servlet中的doGet()、doPost()方法）；
4. 以上就是springmvc的大概执行流程，对这一部分感兴趣的小伙伴可以去深入源码做一个详细的了解，本人是菜鸡一枚，就不在大家面前班门弄斧了；
## 2.3 视图解析器
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020111112293218.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
1. 首先要明确一点，springmvc的页面跳转方式默认是请求转发方式，我们如果不使用视图解析器的话就需要手工写上/WEB-INF/view/show.jsp全路径，在项目静态资源太多时这样肯定会重复的出现/WEB-INF/view/这样的代码，所以视图解析器的作用就出现了
2. 配置了视图解析器以后，我们的所有视图跳转的路径框架会自动为我们加上前缀和后缀。例如上图，controller方法处理完index.jsp页面的请求之后，将页面跳转至/WEB-INF/view/show.jsp，但是我们只写了show，框架根据视图解析器的前缀和后缀为我们补全了路径

# 3、控制器方法剖析
## 3.1 控制器方法接收用前端用户请求数据
### 3.1.1 逐个接收
该种方式适用于前端数据少的请求，例如登录页面，一般只需要用户名和密码以及验证码，这类数据量少的情况就适合使用逐个接收的方式，下面通过一个登录表单看一下控制器方法逐个接收参数。

- 前端表单页

  ```jsp
  <form action="login.do" method="post">
              用户名：<input type="text" name="username"/><br/>
              密码<input type="password" name="userpwd"/><br/>
              <input type="submit" value="注册"/>
  </form>
  ```

- 后台controller

  ```java
  @Controller
  public class MethodController {
  
      @RequestMapping(value = "/login.do",method = RequestMethod.POST)
      /*
          1、@RequestMapping还有一个属性method，该属性用来指定请求方式，若客户同样是发出同一个请求，但是
          请求方式与@RequestMapping的method属性不一致，则报错HTTP状态405-方法不允许
          2、超链接、表单默认是get方式请求
          3、关于post方式中文显示乱码问题的解决方式：
             我们发现当请求方式是post时，客户端的中文数据在结果页展示时会出现乱码，传统的servlet是在doGet、
             doPost方法的第一行执行如下代码统一请求、响应编码格式
             请求：request.setCharacterEncoding("UTF-8");
  
             响应：response.setCharacterEncoding("UTF-8");
                  response.setContentType("text/html;charset=UTF-8");
          4、但是上述的设置编码方式每次在一个方法的第一行都要设置，重复代码过多，在这里我们可以使用过滤器
             对所有请求设置编码格式，一步到位，springMVC内部给我们提供了这样的一个过滤器类CharacterEncodingFilter
       */
  
      public ModelAndView login(String username, String userpwd) {
  
          ModelAndView mv = new ModelAndView();
          mv.addObject("username",username);
          mv.addObject("userpwd",userpwd);
          mv.setViewName("show");
          return mv;
      }
  }
  ```

- 关于中文乱码问题，在传统servlet中，我们接收前台数据之前要设置request字符集，即request.setCharacterEncoding("UTF-8")，同样的后台通过response对象以输出流的方式向浏览器输出信息时也要设置响应字符集；这在springmvc中也是一样的，不过springmvc通过过滤器实现了这一功能，我们只需要在web.xml中加入以下过滤器配置就可以解决中文乱码问题；

  ```xml
  <!--声明过滤器进行编码格式统一-->
      <filter>
          <filter-name>characterEncodingFilter</filter-name>
          <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
          <!--设置项目中使用的字符编码-->
          <init-param>
              <param-name>encoding</param-name>
              <param-value>utf-8</param-value>
          </init-param>
          <!--强制请求对象（request）-->
          <init-param>
              <param-name>forceRequestEncoding</param-name>
              <param-value>true</param-value>
          </init-param>
          <!--强制响应求对象（response）-->
          <init-param>
              <param-name>forceResponseEncoding</param-name>
              <param-value>true</param-value>
          </init-param>
      </filter>
      <filter-mapping>
          <filter-name>characterEncodingFilter</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  ```

- 关于前台数据name值与控制器方法形参名不一致情况解决方案：在开发中，我们一般建议前台数据的name值与控制器方法的形参名一致，若不一致，则可以使用下面的方式解决，还是以一个表单注册为例：

  1. 前台表单

     ```xml
     <form action="different.do" method="post">
         用户名：<input type="text" name="username"/><br/>
         密码<input type="password" name="userpwd"/><br/>
         <input type="submit" value="注册"/>
     </form>
     ```

  2. controller

     ```java
     @Controller
     public class ParamDifferentController {
     
         /*
             解决形参名和用户传入参数名不一致情况（了解即可，很少使用，有点多此一举的意思）
             1、控制器方法的参数名有一个属性@RequestParam，该属性可以解决控制器方法形参名与用户传入实际参数
                名不一致情况。
             2、@RequestParam有两个属性
                value：用户传入实际参数名
                required：布尔值，默认为true，即用户必须传递该实际参数；false则可以不传递该参数
          */
         @RequestMapping(value = "/different.do",method = RequestMethod.POST)
         public ModelAndView different(
                 @RequestParam(value = "username",required = true)String name,
                 @RequestParam(value = "userpwd",required = true)String password) {
     
             ModelAndView mv = new ModelAndView();
             mv.addObject("name",name);
             mv.addObject("password",password);
             mv.setViewName("showDifferent");
             return mv;
         }
     }
     ```

### 3.1.2 对象接收

- 创建一个类Person

  ```java
  //接收数据的普通类
  public class Person {
      private String name;
      private int age;
      public Person() {
          System.out.println("无参构造...");
      }
      public void setName(String name) {
          System.out.println("setName...");
          this.name = name;
      }
      public void setAge(int age) {
          System.out.println("setAge...");
          this.age = age;
      }
      public String getName() {
          return name;
      }
      public int getAge() {
          return age;
      }
      @Override
      public String toString() {
          return "Person{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  '}';
      }
  }
  ```

- 前台表单这次的数据和Person类的属性相同，框架若发现控制器方法的形参为对象并且前端传递过来的数据名与该对象的属性名一致，会自动将这些数据组装为对象，类似于javabean；

  ```xml
  <form action="obj.do" method="post">
      用户名：<input type="text" name="name"/><br/>
      年龄：<input type="text" name="age"/><br/>
      <input type="submit" value="注册"/>
  </form>
  ```

- 后台controller：注意看形参类型（对象）

  ```java
  //使用java对象接收用户数据
  @Controller
  public class ReceiveParamByObjController {
  
      @RequestMapping(value = "/obj.do")
      public ModelAndView byObject(Person person) {
          ModelAndView mv = new ModelAndView();
          mv.addObject("name",person.getName());
          mv.addObject("age",person.getAge());
          mv.setViewName("byObj");
          return mv;
      }
  }
  ```

## 3.2 处理器方法返回值深入

### 3.2.1 返回值为String

处理器方法返回值为String并且该处理器方法定义处上面没有@ResponseBody注解的话，表示该处理方法的作用仅是进行页面的跳转而不向前台传递任何数据。下面是一个返回值为String的controller类

```java
@RequestMapping(value = "/some.do")
    public String returnString() {
        return "show";
    }
```

注意处理器方法返回值为String返回视图时要配合视图解析器使用。

### 3.2.2 返回值为void（了解即可）

控制器方法返回值为void表示即不向前端传递数据，也不进行页面的跳转，所以返回值为void通过用于异步刷新（ajax）。了解即可，因为在实际开发中，我们一般使用返回值为Object的控制器方法响应ajax。

### 3.2.3 返回值为Object（掌握）

控制器方法返回值为Object用于响应ajax请求，先回忆一下传统的servlet响应ajax响应。

- 前端请求，通过一个按钮绑定单击事件实现ajax请求

  ```jsp
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>请求页</title>
  
      <!--js-->
      <script type="text/javascript" src="js/jquery.js"></script>
  
      <script type="text/javascript">
          $(document).ready(function () {
              // 给按钮绑定单击事件
              $("#mybtn").click(function () {
                  // alert("welcome...");
  
                  // ajax
                  $.ajax({
                      // url:"returnVoid.do",
                      url:"returnString.do",
                      data:{
                          name:"zs",
                          age:23
                      },
                      type:"post",
                      // dataType:"json",
                      dataType:"text",
                      success:function (result) {
                          // result从服务器返回的json字符串，即{"name":"zs","age":23}
                          // jquery会把该字符串转换为json对象并赋值给参数result，就可以使用对象.属性来拿值了
                          // alert("name="+result.name+",age="+result.age);
  
                          // // 遍历json对象数组
                          // $.each(result,function (i,element) {
                          //     alert("name="+element.name+",age="+element.age);
                          // })
  
                          // string表示普通字符数据
                          alert(result);
                      }
                  })
              })
          })
  
      </script>
  </head>
  <body>
      <form action="loginByString.do" method="post">
          姓名：<input type="text" name="username"/><br/>
          密码：<input type="password" name="upwd"/><br/>
          <input type="submit" value="提交"/>
      </form>
  
      <p>返回值为void，响应ajax</p>
      <input type="button" value="校验" id="mybtn"/>
  </body>
  </html>
  ```

- 后台servlet

  ```java
  @RequestMapping(value = "/returnVoid.do", method = RequestMethod.POST)
      public void returnVoid(HttpServletResponse response, String name, int age) throws IOException {
          System.out.println("name="+name+"age="+age);
  
          // 处理ajax，使用json传递结果
          Person person = new Person();
          person.setName(name);
          person.setAge(age);
  
          // 定义容器保存json字符串
          String jsonStr = "";
          // 将结果转为json格式数据，使用传统方式
          if (person != null) {
              ObjectMapper om = new ObjectMapper();
              jsonStr = om.writeValueAsString(person);
              System.out.println(jsonStr);
          }
  
          // 将结果响应至客户端
          // 设置响应格式
          response.setContentType("application/json;charset=utf-8");
          // 得到输出流
          PrintWriter out = response.getWriter();
          out.println(jsonStr);
          // 清管道
          out.flush();
          // 关闭流
          out.close();
      }
  ```

传统的servlet处理ajax请求还是比较麻烦的，尤其是通过request、response对象向数据域进行数据设置时；那么通过框架是怎么响应ajax的呢?

1. 首先引入依赖

   ```xml
   <!--Jackson依赖-->
       <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-core</artifactId>
         <version>2.9.0</version>
       </dependency>
       <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-databind</artifactId>
         <version>2.9.0</version>
       </dependency>
     </dependencies>
   ```

2. 在springmvc配置文件中加入注解驱动标签告诉springmvc'框架使用框架功能处理json对象，具体是框架发现注解@ResponseBody，会将该注解对应的控制器方法特殊对待

   ```xml
   <!--加入注解驱动-->
   <mvc:annotation-driven/>
   ```

3. 下面是controller类

   ```java
    @RequestMapping(value = "/returnAnnotation.do", method = RequestMethod.POST)
       // 加入注解
       @ResponseBody
       public Object returnObj(String name, int age) {
   
           /**
            * 使用springmvc框架向前端传递json数据的步骤
            * 1、加入处理json的工具类依赖，springmvc默认使用jackkjson
            * 2、在springmvc配置文件中加入注解驱动<annotation-driven>
            *    在创建springmvc容器时，注解驱动会执行一下两行代码
            *    ObjectMapper om = new ObjectMapper();
            *    jsonStr = om.writeValueAsString(person);
            *    即注解驱动干的事就是将java对象转换为json格式的数据
            * 3、在控制器方法上加入@ResponseBody注解，相当于执行了一下代码
            *    response.setContentType("application/json;charset=utf-8");
            *    PrintWriter out = response.getWriter();
            *    out.println(jsonStr);
            *    即将json格式数据发送给服务器
            */
   
           // 包装service处理后的数据
           Person person = new Person();
           person.setName("李四");
           person.setAge(24);
           return person;// 框架自动将其转换为json格式发给前台浏览器
       }
   ```

### 3.2.4返回值为ModeAndView

ModeAndView即可以使用ModeAndView.addObject(key,value)向服务器作用域（request域）添加数据，又可以使用ModeAndView.setViewName(value)实现页面的跳转（内部是请求转发方式进行的跳转），代码就如入门案例所示，这里就不给出了，ModeAndView是我们用的最多的。

# 4、SSM整合开发

## 4.1 整合思路梳理

在上篇我的Spring博客中详细说明Spring+Mybatis整合，在这里的思路是一样的，我们还是将Mybatis的SqlSessionFactory对象交由spring容器创建。与上次不同的是，这次spring容器不光要托管SqlSessionFactory对象，还要将service、dao对象的创建；Mybatis负责dao层；springmvc完成controller和web开发相关的对象创建。即前端发出请求->controller拦截->调用service处理业务->调用dao查询数据库。查询完将结果一层一层返回给前端用户。

## 4.2 项目搭建

1. 创建maven项目，导入依赖，由于是整合，涉及的依赖较多，下面是依赖

   ```xml
   <!--servlet依赖-->
       <dependency>
         <groupId>javax.servlet</groupId>
         <artifactId>javax.servlet-api</artifactId>
         <version>3.1.0</version>
         <scope>provided</scope>
       </dependency>
       <!-- jsp依赖 -->
       <dependency>
         <groupId>javax.servlet.jsp</groupId>
         <artifactId>jsp-api</artifactId>
         <version>2.2.1-b03</version>
         <scope>provided</scope>
       </dependency>
       <dependency>
         <groupId>org.springframework</groupId>
         <artifactId>spring-webmvc</artifactId>
         <version>5.2.5.RELEASE</version>
       </dependency>
       <dependency>
         <groupId>org.springframework</groupId>
         <artifactId>spring-tx</artifactId>
         <version>5.2.5.RELEASE</version>
       </dependency>
       <dependency>
         <groupId>org.springframework</groupId>
         <artifactId>spring-jdbc</artifactId>
         <version>5.2.5.RELEASE</version>
       </dependency>
       <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-core</artifactId>
         <version>2.9.0</version>
       </dependency>
       <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-databind</artifactId>
         <version>2.9.0</version>
       </dependency>
       <dependency>
         <groupId>org.mybatis</groupId>
         <artifactId>mybatis-spring</artifactId>
         <version>1.3.1</version>
       </dependency>
       <dependency>
         <groupId>org.mybatis</groupId>
         <artifactId>mybatis</artifactId>
         <version>3.5.1</version>
       </dependency>
       <dependency>
         <groupId>mysql</groupId>
         <artifactId>mysql-connector-java</artifactId>
         <version>5.1.9</version>
       </dependency>
       <dependency>
         <groupId>com.alibaba</groupId>
         <artifactId>druid</artifactId>
         <version>1.1.12</version>
       </dependency>
   ```

2. 创建mybatis核心配置文件

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <configuration>
   
       <typeAliases>
           <package name="com.mms.entity"/>
       </typeAliases>
       <mappers>
           <!--批量加载mapper映射文件，要求mapper文件与mapper接口名字一样（包括大小写）-->
           <package name="com.mms.mapper"/>
       </mappers>
   </configuration>
   ```

3. 创建spring配置文件applicationContext.xml

   ```xml
   <!--组件扫描器-->
       <context:component-scan base-package="com.mms.service"/>
       <!--加载数据库信息-->
       <context:property-placeholder location="classpath:db.properties"/>
       <!--声明数据源-->
       <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
             init-method="init" destroy-method="close">
           <property name="url" value="${url}"/>
           <property name="username" value="${username}"/>
           <property name="password" value="${password}"/>
       </bean>
       <!--声明SqlSessionFactory对象-->
       <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
           <property name="dataSource" ref="dataSource"/>
           <property name="configLocation" value="classpath:mybatis-config.xml"/>
       </bean>
       <!--生成mapper接口对象-->
       <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
           <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
           <property name="basePackage" value="com.mms.mapper"/>
       </bean>
   ```

4. 创建springmvc配置文件springmvc.xml

   ```xml
   <!--组件扫描器，创建controller对象-->
       <context:component-scan base-package="com.mms.controller"/>
       <!--视图解析器-->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
           <property name="prefix" value="/WEB-INF/view/"/>
           <property name="suffix" value=".jsp"/>
       </bean>
       <!--注解驱动-->
       <mvc:annotation-driven/>
       <!--处理静态资源-->
       <mvc:resources mapping="/js/**" location="/js/"/>
   ```

5. web.xml配置文件与之前的相同

6. 创建数据库配置文件db.properties

   ```xml
   url = jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true&serverTimezone=UTC
   username = xxx
   password = xxx
   ```

7. 项目结构
![ssm整合结构](https://img-blog.csdnimg.cn/20201111123042748.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
8. 项目流程
![ssm整合项目流程](https://img-blog.csdnimg.cn/20201111123115144.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)
   用户通过前台页面超链接发出增加学生或是查询全部的请求，该请求会被中央调度器拦截并分发给相应的controller处理，controller有一个service成员变量，controller调用service的业务方法从而service层接着调用dao层完成对数据库的操作，典型的mvc设计模式。

## 4.3 项目源码

1. controller

   ```java
   @Controller
   @RequestMapping(value = "/student")
   public class StudentController {
   
       // 自动注入
       @Autowired
       private StudentServiceImpl studentService;
   
       // 控制器方法：查询全部
       @RequestMapping(value = "/findAll")
       @ResponseBody // 响应ajax
       public List<Student> findAll() {
            return studentService.findAll();
       }
       // 控制器方法：增加学生
       @RequestMapping(value = "/addStudent")
       public ModelAndView addStudent(Student student) {
           ModelAndView mv = new ModelAndView();
           int count = studentService.addStudent(student);
           if (count > 0) {
               mv.addObject("msg","注册成功");
               mv.addObject("name",student.getStuName());
               mv.setViewName("success");
           } else {
               mv.addObject("msg","注册失败");
               mv.setViewName("fail");
           }
           return mv;
       }
   }
   ```

2. service

   ```java
   @Service(value = "studentService")
   public class StudentServiceImpl implements IStudentService {
       // 自动注入
       @Autowired
       private StudentMapper studentMapper;
   
       @Override
       public List<Student> findAll() {
           return studentMapper.findAll();
       }
   
       @Override
       public int addStudent(Student student) {
           return studentMapper.addStudent(student);
       }
   }
   ```

3. mapper接口和映射文件

   ```java
   // mapper接口
   public interface StudentMapper {
   
       /**
        * 查询全部
        * @return
        */
       List<Student> findAll();
   
       /**
        * 增加学生
        * @param student
        * @return
        */
       int addStudent(Student student);
   }
   ```

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="com.mms.mapper.StudentMapper">
   
       <!--查询全部-->
       <select id="findAll" resultType="Student">
           select stuno, stuname
           from student
       </select>
   
       <!--增加学生-->
       <insert id="addStudent" parameterType="Student">
           insert into student (stuno,stuname)
           values (#{stuNo},#{stuName})
       </insert>
   </mapper>
   ```

4. 前台jsp，请求页

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>注册页</title>
   </head>
   <body>
       <div align="center">
           <a href="register.jsp">注册</a><br/>
           <a href="show.jsp">查询全部</a>
       </div>
   </body>
   </html>
   ```

5. 前台jsp，注册页

   ```jsp
   <div align="center">
           <form action="student/addStudent">
               学号：<input type="text" name="stuNo"><br/>
               姓名：<input type="text" name="stuName"/><br/>
               <input type="submit" value="注册"/>
           </form>
   </div>
   ```

6. 前台jsp，展示页

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>学生信息页</title>
       <!--引入js-->
       <script type="text/javascript" src="js/jquery.js"></script>
       <script type="text/javascript">
   
           // 浏览器加载函数
           $(function () {
               showStudent();
           })
   
           // 异步刷新函数
           function showStudent() {
               $.ajax({
                   url:"student/findAll",
                   type:"post",
                   dataType:"json",
                   success:function (result) {
                       // 每次异步刷新时重新展示页面
                       $("#tbody").html("");
   
                       // 循环遍历json数组，将响应结果写入表格体
                       $.each(result,function (index,element) {
                           $("#tbody").append("<tr>").append("<td>"+element.stuNo+"<td>")
                           .append("<td>"+element.stuName+"<td>").append("<tr>");
                       })
                   }
               })
           }
       </script>
   </head>
   <body>
       <div align="center">
           <p>学生信息</p>
           <table>
               <thead>
                   <tr>
                       <td>学号</td>
                       <td>姓名</td>
                   </tr>
               </thead>
               <tbody id="tbody">
   
               </tbody>
           </table>
       </div>
   </body>
   </html>
   ```

#  5、绝对路径、相对路径、静态资源问题

## 5.1 绝对路径、相对路径

相信各位在初学javaweb时，访问项目时常会出现404资源不存在，当初我学习时也是同样的情况，一直搞不懂斜杠"/"到底是什么意思，xml中的斜杠和普通jsp页面中的斜杠是一样的吗诸如此类问题，直到我在学习完springmvc后，自己通过测试才弄懂绝对路径、相对路径以及斜杠的意思。下面就听我一一道来。

1. 绝对路径：带斜杠"/"的地址

   直接说结论：

   a、在web（eclipse中是webContent）下的所有请求若以斜杠开头（绝对地址），此时的斜杠就代表服务器的根目录，即localhost:port/，此类的请求发出必是404，因为少了请求url中少了项目名。
      解决方法：显示的在请求的斜杠前使用上下文对象（request.getContextPath）加上项目的名称

   b、在servlet中的请求若以斜杆开头，此时的斜杠就代表项目的根目录，即localhost:port/项目名/， 不需要开发人员指定上下文对象

2. 相对路径：不带斜杠"/"的地址，相对于当前资源所在的目录；例如现在有一个index.jsp，它的完整url如下
       localhost:8080/项目名/index.jsp，那么它的相对地址就是相对于当前资源所在的路径，即     localhost:8080/项目名/，相对地址就是相对于当前资源所在的路径；./代表当前路径，../代表当前路径的父级目录。

3. 关于xml、jsp中斜杠的意义

   a、xml中的斜杠代表项目根目录，即localhost:8080/项目名

   b、jsp中超链接、表单action请求中的斜杠均代表的是绝对路径

   c、所有的后台代码（controller）中的斜杠的斜杠代表项目根

## 5.2 静态资源问题

为什么要引入这个问题，因为我们现在的所有项目的程序入口DispacherServlet的url-pattern都是*.do这类扩展名方式，若是我们将中央调度器的url-pattern改为/会出现静态资源（图片、css、js等）会报404，为什么会出现这样的问题呢？原因如下：

1. 我们现在的中央调度器的url-pattern都是为"*.do"这样的，当发出的请求匹配url-pattern时会调用相应的
   servlet来处理；那些不匹配的请求会交由tomcat内置的默认servlet这个类来处理（例如静态资源css、jsp等）
   但是当中央调度器的url-pattern为"/"时（表示拦截一切），此时的servlet的默认servlet就被替代了，
   中央调度器默认是没有处理静态资源的能力，所以会出现静态资源找不到的问题。

2. 解决方案：
      方案1、在springmvc配置文件中加入标签<mvc:default-servlet-handler>
            原理：再加入这个标签时，在创建springmvc容器时，会创建一个DefaultServletHttpRequestHandler对象，该对象会将所有的静态资源的访问交由tomcat内置的defaultservlet来处理（内部是请求转发形式）
            注意：该方式会将所有的请求交由tomcat默认servlet去处理，会覆盖@RequestMapping的请求，需要加入 注解驱动标签

      	方案2：在springmvc配置文件中加入标签<mvc:resources mapping="" location="">
      	     批量处理静态资源

# 6、SpringMVC处理异常

## 6.1 概述

1、在以前的程序中，我们都是使用try、catch进行异常的处理，但是这样有一个弊端，那就是当我们的程序涉及
  到多个异常时，程序会出现很多的try、catch语句块，造成程序的冗余
2、在springmvc中，我们可以使用一个类来专门处理controller类中抛出的异常类，从而减少重复代码，这也是
   aop的体现。
3、这个专门处理controller类抛出异常的类的结构：
1）我们在类的声明语句上面要加入注解@ControllerAdvice，这个注解会告诉springmvc框架这个类是全局
  异常处理类。在全局异常处理类中定义处理各个异常的方法，这些方法的上面要加入@ExceptionHandler(value="异常类.class")。
  告诉框架这个方法是处理异常类的方法
4、执行原理：
   1)、首先当controller类中抛出异常时，框架发现在springmvc配置文件中的组件扫描器中的一个包下有一个
       类的声明处有@ControllerAdvice注解，就会知道这个类是全局异常处理类
   2)、当controller类中抛出异常时，会拿着该抛出异常类的类型与全局异常处理类的方法的
       @ExceptionHandler(value="异常类.class") 的value值异常类类型进行比对，若两者相同则
       进入方法执行相应代码；若不同则接着与剩下的方法的@ExceptionHandler(value="异常类.class")
       进行比对；直至最后进入默认的异常处理方法
5、开发步骤：
   1）新建maven项目
   2）引入依赖
   3）创建一个自定义异常类，再定义它的n个子类，由于表示不同的异常类
   4）创建全局异常处理类
   5）创建Springmvc配置文件，加入注解扫描器，值为controller、全局异常处理类所在的包；声明注解驱动

## 6.2 项目结构与代码

### 6.2.1 项目结构
![处理异常项目结构](https://img-blog.csdnimg.cn/20201111123519696.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)

### 6.2.2 相关重要性代码

1. 三个异常类代码：

   ```java
   // 自定义异常类父类
   public class MyException extends Exception{
   
       public MyException() {
           super();
       }
   
       public MyException(String message) {
           super(message);
       }
   }
   ```

   ```java
   // 年龄异常类
   public class AgeException extends MyException{
   
       public AgeException() {
           super();
       }
   
       public AgeException(String message) {
           super(message);
       }
   }
   ```

   ```java
   // 姓名异常类
   public class NameException extends MyException {
       public NameException() {
           super();
       }
   
       public NameException(String message) {
           super(message);
       }
   }
   ```

2. 控制器

   ```java
   @Controller
   public class StudentController {
   
       @RequestMapping(value = "/some.do")
       public ModelAndView someDo(String name, Integer age) throws MyException {
           ModelAndView mv = new ModelAndView();
           // 进行年龄、姓名的判断
           if (!("zs".equals(name))) {
               // 姓名不是zs，抛出NameException
               throw new NameException("姓名不正确");
           }
           if (!(age > 20 && age < 30)) {
               // 年龄不合法，抛出AgeException异常
               throw new AgeException("年龄不合法");
           }
           // 说明姓名、年龄合法，跳转到登陆成功页面
           mv.addObject("msg","登陆成功，欢迎您");
           mv.addObject("name",name);
           mv.setViewName("success");
           return mv;
       }
   }
   ```

3. 全局异常处理类

   ```java
   // 全局异常处理类
   @ControllerAdvice
   public class ExceptionsController {
   
       // 姓名异常类处理方法
       @ExceptionHandler(value = NameException.class)
       public ModelAndView nameException(Exception exception) {
           // 形参exception就代表controller类中抛出的异常对象
           ModelAndView mv = new ModelAndView();
           // 进行异常类的数据、视图的处理
           mv.addObject("msg","姓名不合法，必须为zs");
           // 将异常信息发送至前端进行处理，提升用户感知
           mv.addObject("exception",exception);
           mv.setViewName("nameError");
           return mv;
       }
   
       // 年龄异常类处理方法
       @ExceptionHandler(value = AgeException.class)
       public ModelAndView ageException(Exception exception) {
           ModelAndView mv = new ModelAndView();
           mv.addObject("msg","年龄不合法，必须在20至30之间");
           mv.addObject("exception",exception);
           mv.setViewName("ageError");
           return mv;
       }
   
       // 默认异常处理方法
       @ExceptionHandler
       public ModelAndView defaultException(Exception exception) {
           ModelAndView mv = new ModelAndView();
           mv.addObject("msg","程序发生了一个不可预知的问题");
           mv.addObject("exception",exception);
           mv.setViewName("defaultError");
           return mv;
       }
   }
   ```

# 7、拦截器

## 7.1 拦截器执行流程

### 7.1.1 拦截器定义以及拦截器方法

在javaweb阶段我们就知道，要想让一个普通的类具有某种特定功能，要嘛通过继承、要嘛通过实现接口，亦或是通过注解这三种方式可以使一个普通类变得不普通，拦截器也是这样，springmvc框架说了要实现拦截器必须实现HandlerInterceptor接口，我们可以看一下这个接口的定义：

```java
public interface HandlerInterceptor {
    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    default void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    default void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
```

关于这个接口三个方法：

1. preHandle：处理请求
    1、该方法在处理器方法执行之前执行。其返回值为boolean，若为true，则紧接着会执行处理器方
    法（放行），且一定会执行afterCompletion方法
    2、Object handler：被拦截的控制器对象
2. postHandle：处理响应
   1、若preHandle方法返回true，那么控制器方法执行完毕以后会执行postHandle
   2、ModelAndView modelAndView代表控制器方法的返回值，故可以在postHandle改变控制器
     方法的返回值（即数据和视图）
3. afterCompletion：当页面被渲染完毕之后触发该方法
   1、什么叫做渲染完毕：即将jsp中的图片、js、css等组装完毕之后就叫做页面渲染完毕
   2、只要preHandle方法的返回值为true，则一定执行afterCompletion方法

### 7.1.2 拦截器工作流程

下面通过一张图看一下一个拦截器是怎么工作的。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111123648708.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21tczUyMHd3dw==,size_16,color_FFFFFF,t_70#pic_center)


1. 首先前端用户发出hello请求，该请求首先会被拦截器的preHandle方法所拦截，若该方法返回值为true，则放行该请求并分发给相应的controller方法进行处理；若返回值为false，则hello请求被截断，不会到达相应的controller；
2. 处理器方法处理完hello请求之后就该向前端进行响应，响应经过拦截器时会执行postHandle方法，在该方法内可以拿到controller方法处理用户请求的结果并可以对结果进行修改；
3. 最后会进行页面的选择以及渲染，页面渲染完毕以后会执行afterCompletion方法；

## 7.2 案例实现

用户通过前台通过表单提交个人信息，拦截器校验用户信息，若用户名为zs则放行，否则截断请求。

1. 前台jsp，请求

   ```jsp
   <div align="center">
           <form action="student.do">
               姓名：<input type="text" name="name"/><br/>
               年龄：<input type="text" name="age"/><br/>
               <input type="submit" value="提交"/>
           </form>
   </div>
   ```

2. 拦截器

   ```java
   public class MyInterceptor implements HandlerInterceptor {
       
       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           System.out.println("preHandler...");
           return true;
       }
       @Override
       public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
           System.out.println("postHandler...");
       }
       @Override
       public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
           System.out.println("afterCompletion...");
       }
   }
   ```

3. controller

   ```java
   @Controller
   public class StudentController {
   
       @RequestMapping(value = "/student.do")
       public ModelAndView someDo(String name, Integer age) {
           System.out.println("someDo...");
           ModelAndView mv = new ModelAndView();
           mv.addObject("name",name);
           mv.addObject("age",age);
           mv.setViewName("success");
           return mv;
       }
   }
   ```

4. 前台jsp，结果页

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>登陆</title>
   </head>
   <body>
       登陆成功，欢迎您：
       <p>${name}</p>
   </body>
   </html>
   ```

# 8、补充

至此，SSM框架阶段告一段落了，小编也可以快乐的玩耍了，为了让各位的阅读博文的体验更加好，我也听取了前辈的建议，将之前的Mybatis、Spring以及本篇SpringMVC的代码和笔记上传到了github和gitee仓库，供大家参考，下面是仓库的链接，希望大家一起进步。

[github仓库地址](https://github.com/musha-Ma/SSMProject)

[gitee仓库地址](https://gitee.com/musha-ma/ssmproject)