ch01-hello-springmvc：SpringMVC入门案例，用户通过点击超链接跳转页面

1、环境搭建
    1）新建maven项目
    2）导入依赖
        springmvc依赖，在导入springmvc依赖时会将其依赖的额外包导入（即spring依赖，因为springmvc
        是spring的一个模块）
    3）整理项目结构，在main下加入java、resources目录
    4）注册springmvc中央调度器DisPatcherServlet
    5）创建请求页面
    6）创建处理请求的controller类，并定义请求方法
    7）创建结果页
    8）启动tomcat服务器执行请求

