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
                    // 使结果只显示一次
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
