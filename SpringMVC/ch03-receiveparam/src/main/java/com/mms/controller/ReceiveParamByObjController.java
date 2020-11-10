package com.mms.controller;

import com.mms.vo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//使用java对象接收用户数据
@Controller
public class ReceiveParamByObjController {

    @RequestMapping(value = "/obj.do")
    public ModelAndView byObject(Person person) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",person.getName());
        mv.addObject("age",person.getAge());
        mv.setViewName("byObj");
        return mv;
    }
}
