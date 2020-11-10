package com.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ParamDifferentController {

    /*
        解决形参名和用户传入参数名不一致情况（了解即可，很少使用，有点多此一举的意思）
        1、控制器方法的参数名有一个属性@RequestParam，该属性可以解决控制器方法形参名与用户传入实际参数
           名不一致情况。
        2、@RequestParam有两个属性
           value：用户传入实际参数名
           required：布尔值，默认为true，即用户必须传递该实际参数；false则可以不传递该参数
     */
    @RequestMapping(value = "/different.do",method = RequestMethod.POST)
    public ModelAndView different(
            @RequestParam(value = "username",required = true)String name,
            @RequestParam(value = "userpwd",required = true)String password) {

        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.addObject("password",password);
        mv.setViewName("showDifferent");
        return mv;
    }
}
