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
