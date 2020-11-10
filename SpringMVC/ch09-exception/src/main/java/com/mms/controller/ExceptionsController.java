package com.mms.controller;

import com.mms.exception.AgeException;
import com.mms.exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// 全局异常处理类
@ControllerAdvice
public class ExceptionsController {

    // 姓名异常类处理方法
    @ExceptionHandler(value = NameException.class)
    public ModelAndView nameException(Exception exception) {
        // 形参exception就代表controller类中抛出的异常对象
        ModelAndView mv = new ModelAndView();
        // 进行异常类的数据、视图的处理
        mv.addObject("msg","姓名不合法，必须为zs");
        // 将异常信息发送至前端进行处理，提升用户感知
        mv.addObject("exception",exception);
        mv.setViewName("nameError");
        return mv;
    }

    // 年龄异常类处理方法
    @ExceptionHandler(value = AgeException.class)
    public ModelAndView ageException(Exception exception) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","年龄不合法，必须在20至30之间");
        mv.addObject("exception",exception);
        mv.setViewName("ageError");
        return mv;
    }

    // 默认异常处理方法

    @ExceptionHandler
    public ModelAndView defaultException(Exception exception) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","程序发生了一个不可预知的问题");
        mv.addObject("exception",exception);
        mv.setViewName("defaultError");
        return mv;
    }
}
