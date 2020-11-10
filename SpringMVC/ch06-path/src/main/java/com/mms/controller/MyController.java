package com.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MyController {


    @RequestMapping(value = "/user/some.do")
    public String loginDoByString(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 页面跳转
        return "show.jsp";// 404，后台向前端跳转应该使用绝对路径（不配置视图解析的情况下）
    }

}
