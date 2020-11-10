package com.mms.controller;

import com.mms.exception.AgeException;
import com.mms.exception.MyException;
import com.mms.exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @RequestMapping(value = "/some.do")
    public ModelAndView someDo(String name, Integer age) throws MyException {
        ModelAndView mv = new ModelAndView();
        // 进行年龄、姓名的判断
        if (!("zs".equals(name))) {
            // 姓名不是zs，抛出NameException
            throw new NameException("姓名不正确");
        }
        if (!(age > 20 && age < 30)) {
            // 年龄不合法，抛出AgeException异常
            throw new AgeException("年龄不合法");
        }
        // 说明姓名、年龄合法，跳转到登陆成功页面
        mv.addObject("msg","登陆成功，欢迎您");
        mv.addObject("name",name);
        mv.setViewName("success");
        return mv;
    }
}
