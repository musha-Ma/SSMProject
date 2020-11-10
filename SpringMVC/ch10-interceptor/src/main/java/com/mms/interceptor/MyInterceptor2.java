package com.mms.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截器
public class MyInterceptor2 implements HandlerInterceptor {

    /*
        处理请求方法：
         1、该方法在处理器方法执行之前执行。其返回值为boolean，若为true，则紧接着会执行处理器方
         法（放行），且一定会执行afterCompletion方法
         2、Object handler：被拦截的控制器对象
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandler222...");
        return false;

        /*  执行结果：
            preHandler...
            preHandler222...
            afterCompletion...
         */
    }

    /*
        处理响应方法：
        1、若preHandle方法返回true，那么控制器方法执行完毕以后会执行postHandle
        2、ModelAndView modelAndView代表控制器方法的返回值，故可以在postHandle改变控制器
          方法的返回值（即数据和视图）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler222...");
    }

    /*
        当页面被渲染完毕之后触发该方法
        1、什么叫做渲染完毕：即将jsp中的图片、js、css等组装完毕之后就叫做页面渲染完毕
        2、只要preHandle方法的返回值为true，则一定执行afterCompletion方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion222...");
    }
}
