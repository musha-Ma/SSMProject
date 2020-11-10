<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请求页</title>
</head>
<body>
    <p>转发</p>
    <form action="forward.do" method="post">
        姓名：<input type="text" name="name"/><br/>
        年龄：<input type="text" name="age"/><br/>
        <input type="submit" value="提交"/>
    </form>

    <p>重定向</p>
    <form action="redirect.do" method="post">
        姓名：<input type="text" name="name"/><br/>
        年龄：<input type="text" name="age"/><br/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
