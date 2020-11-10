<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结果页</title>
</head>
<body>
    <%
        // 使用上下文对象解决路径问题，下面的第一行path就是项目名，第二行就是完整url
        String path = request.getContextPath();
    %>
    <p>request中的数据如下</p>
    姓名：${username}<br/>
    密码：${upwd}<br/>
    <img src="<%=path%>/static/imgs/my.JPG">
</body>
</html>
