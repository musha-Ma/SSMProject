
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页</title>
</head>
<body>
    <p>欢迎注册</p>
    <form action="register" method="post">
        <table>
            <%--
                表格由table标签开始，tr表示行数，td表示列数
                例如本例就是一个5行两列的表格
            --%>
            <tr>
                <td>学号</td>
                <td><input type="text" name="stuno"></td>
            </tr>
            <tr>
                <td>姓名</td>
                <td><input type="text" name="stuname"></td>
            </tr>
            <tr>
                <td>学生证</td>
                <td><input type="text" name="cardid"></td>
            </tr>
            <tr>
                <td>班级</td>
                <td><input type="text" name="classid"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="提交"></td>
            </tr>
        </table>
    </form>
</body>
</html>
