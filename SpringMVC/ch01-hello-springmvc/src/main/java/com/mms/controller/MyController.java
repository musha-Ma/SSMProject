package com.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
    控制器类：使用@Controller注解修饰，在DispacherServlet对象在读取springmvc配置文件时通过组件扫描器
            进行实例化
 */
@Controller
public class MyController {

    /*
        @RequestMapping(value = "/Xxx")：请求映射，将一个请求与一个方法绑定起来，一个请求指定相应
                                         方法进行处理
        1、value值唯一，表示请求的路径，其中斜杠"/"代表项目根路径
        2、使用ReuestMapper修饰的方法叫做处理器方法、控制器方法，相当于传统servlet中的doGet()、doPost
        3、返回值ModeAndView：
            Mode：数据，即处理结果
            View：视图，例如jsp
     */
    @RequestMapping(value = "/some.do")
    public ModelAndView someDo() {

        //代码到这里，说明进入了someDo方法了，创建ModelAndView对象来封装结果
        ModelAndView mv = new ModelAndView();
        //添加数据，类似于request.setAttribute(key,value)
        mv.addObject("msg","第一个springmvc项目");
        mv.addObject("function","执行了someDo方法");
        //指定视图
        //mv.setViewName("/WEB-INF/view/show.jsp");

        //使用视图解析器
        mv.setViewName("show");
        return mv;
    }
}
