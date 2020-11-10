<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请求页</title>
</head>
<body>

    <form action="view/some.do" method="post">
        用户名：<input type="text" name="username"/><br/>
        密码<input type="password" name="userpwd"/><br/>
        年龄：<input type="text" name="age"/><br/>
        <input type="submit" value="注册"/>
    </form>
</body>
</html>
