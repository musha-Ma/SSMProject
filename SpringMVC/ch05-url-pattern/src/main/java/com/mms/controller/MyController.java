package com.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {


    @RequestMapping(value = "/loginByString.do", method = RequestMethod.POST)
    public String loginDoByString(HttpServletRequest request, String username, String upwd) {

        // 使用request域传递数据
        request.setAttribute("username",username);
        request.setAttribute("upwd",upwd);

        // 页面跳转
        return "show";
    }

}
