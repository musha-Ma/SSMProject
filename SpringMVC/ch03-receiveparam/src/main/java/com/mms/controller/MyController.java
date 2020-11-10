package com.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/view")
public class MyController {

    @RequestMapping(value = "/some.do")

    /*
        如何接收客户端数据？
        1、在传统servlet中，我们在doGet、doPost方法中使用request对象的方法来接收客户端的请求数据的
           String value = request.getParameter("name");
        2、在SpringMVC中，我们可以使用控制器方法来接收客户端请求数据。通过处理器方法的形参和客户端请求
           数据一一对应,要求形参名必须和请求数据名一致。 该种方式称为逐个接收数据，适合数据量较少情况
     */
    //逐个接收参数
    public ModelAndView someDo(String username,String userpwd,Integer age) {

        /*
            1、此时参数username就是表单传递过来的数据username，同理passwd就是用户密码数据，类似与
               在SpringMVC内部执行了以下代码：
               String username = request.getParameter("username");
               左边的一部分就是控制器方法的一个参数，userpwd类似
            2、需要注意的是关于非String类型的数据在转换可能会出现错误，例如用户的年龄是一个整型数字num
               SpringMVC框架内部会执行以下代码：
               int age = Integer.parseInt(request.getParameter("age"))
               一般建议使用Integer包装类接收数字数据，这样在数据为空时不会报错空指针异常
         */
        ModelAndView mv = new ModelAndView();
        //添加数据，类似于request.setAttribute(key,value)
        mv.addObject("username",username);
        mv.addObject("userpwd",userpwd);
        mv.addObject("age",age);
        mv.setViewName("show");
        return mv;

    }
}
