package com.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
    @RequestMapping置于控制器类声明上方：
    1、若是同一个控制器类中的控制方法的@RequestMapping的value值有公共的请求地址段，则可以将该公共地址段
       放入控制器类上方的@RequestMapping的value属性部分，这样的声明方式叫做模块名称
 */
@Controller
@RequestMapping(value = "/user")
public class MyController {

    @RequestMapping(value = "/some.do") //结合类声明的@RequestMapping得完整请求路径/user/some.do
    public ModelAndView someDo() {
        ModelAndView mv = new ModelAndView();
        //添加数据，类似于request.setAttribute(key,value)
        mv.addObject("msg","第一个springmvc项目");
        mv.addObject("function","执行了someDo方法");
        mv.setViewName("show");
        return mv;
    }
}
