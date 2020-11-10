package com.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
