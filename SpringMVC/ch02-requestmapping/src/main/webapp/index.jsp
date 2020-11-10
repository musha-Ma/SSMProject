<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请求页</title>
</head>
<body>
    <!--
        重点：xml中的斜杠"/"和jsp中的斜杠"/"问题
        1、xml中的斜杠代表项目根目录，即localhost:8080/项目名
           例如本项目如果在xml中斜杠就代表如下
           http://localhost:8080/ch02_requestmapping/
        2、超链接、表单action请求中的斜杠均代表的是绝对路径
           例如<a href="/"></a>就代表如下
           http://localhost:8080/，这样肯定是404，
           如果想要让超链接、action请求直接是跟在项目根路径下，不能加斜杠（即代表相对路径，相对于
           当前web应用），如下面的超链接<a href="user/some.do"></a>就代表如下
           http://localhost:8080/ch02_requestmapping/action
    -->
    <a href="user/some.do">hello,SpringMVC</a>
</body>
</html>
