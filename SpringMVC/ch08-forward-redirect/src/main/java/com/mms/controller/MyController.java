package com.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    /**
     * 请求转发：在ModelAndView.setviewname("path")中的访问路径不受视图解析器的影响，即path是哪里
     *         页面就跳转到哪里，常用来指定不在视图解析中的资源路径
     * 使用方法：ModelAndView.setviewname("forward:path")
     * @return
     */
    @RequestMapping(value = "/forward.do")
    public ModelAndView forward(String name, Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.addObject("age",age);
        // 显式的指定请求路径
        mv.setViewName("forward:/WEB-INF/view/show.jsp");
        return mv;
    }

    /**
     * 重定向：
     *        1、客户发送一个请求到服务器，服务器匹配servlet，这都和请求转发一样，servlet处理
     *          完之后调用了sendRedirect()这个方法，这个方法是response的方法，所以，当这个servlet
     *          处理完之后，看到response.senRedirect()方法，立即向客户端返回这个响应，响应行告诉
     *          客户端你必须要再发送一个请求，去访问redirect.jsp，紧接着客户端受到这个请求后，立刻
     *          发出一个新的请求，去请求test.jsp,这里两个请求互不干扰，相互独立，在前面request里面
     *          setAttribute()的任何东西，在后面的request里面都获得不了。可见，在sendRedirect()
     *          里面是两个请求，两个响应。
     *        2、由于是不同的请求，重定向传递的参数会在地址栏显示，所以传递时要对中文编码进行处理。
     *          若想在第二次请求中拿到第一次的请求参数使用${param.name}，因为重定向会将第一次的请求
     *          参数加入到第二次请求url之后，相当于一次get请求，又暴露数据的风险
     *        3、重定向不能访问WEB-INF下的资源，转发可以
     * @param name
     * @param age
     * @return
     */
    @RequestMapping(value = "/redirect.do")
    public ModelAndView redirect(String name, Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.addObject("age",age);
        mv.setViewName("redirect:/redirect.jsp");
        return mv;
    }

}
