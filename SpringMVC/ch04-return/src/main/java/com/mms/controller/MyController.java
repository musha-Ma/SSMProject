package com.mms.controller;

import com.mms.vo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {

    /*
    @RequestMapping(value = "/loginByString.do", method = RequestMethod.POST)
    public String loginDoByString(HttpServletRequest request,String username, String upwd) {

        // 使用request域传递数据
        request.setAttribute("username",username);
        request.setAttribute("upwd",upwd);

        // 页面跳转
        return "show";
    }*/

    /*
        控制器方法为void
        1、特点：既不进行页面的跳转，也不传递数据
        2、用处：响应ajax，ajax只有数据，没有视图

    @RequestMapping(value = "/returnVoid.do", method = RequestMethod.POST)
    public void returnVoid(HttpServletResponse response, String name, int age) throws IOException {
        System.out.println("name="+name+"age="+age);

        // 处理ajax，使用json传递结果
        Person person = new Person();
        person.setName(name);
        person.setAge(age);

        // 定义容器保存json字符串
        String jsonStr = "";
        // 将结果转为json格式数据，使用传统方式
        if (person != null) {
            ObjectMapper om = new ObjectMapper();
            jsonStr = om.writeValueAsString(person);
            System.out.println(jsonStr);
        }

        // 将结果响应至客户端
        // 设置响应格式
        response.setContentType("application/json;charset=utf-8");
        // 得到输出流
        PrintWriter out = response.getWriter();
        out.println(jsonStr);
        // 清管道
        out.flush();
        // 关闭流
        out.close();
    }*/

    /*
        返回值是Object
        1、返回值object代表返回的是数据，和视图无关
        2、用处：响应ajax
     */
    @RequestMapping(value = "/returnAnnotation.do", method = RequestMethod.POST)
    // 加入注解
    @ResponseBody
    public Object returnObj(String name, int age) {

        /**
         * 使用springmvc框架向前端传递json数据的步骤
         * 1、加入处理json的工具类依赖，springmvc默认使用jackkjson
         * 2、在springmvc配置文件中加入注解驱动<annotation-driven>
         *    在创建springmvc容器时，注解驱动会执行一下两行代码
         *    ObjectMapper om = new ObjectMapper();
         *    jsonStr = om.writeValueAsString(person);
         *    即注解驱动干的事就是将java对象转换为json格式的数据
         * 3、在控制器方法上加入@ResponseBody注解，相当于执行了一下代码
         *    response.setContentType("application/json;charset=utf-8");
         *    PrintWriter out = response.getWriter();
         *    out.println(jsonStr);
         *    即将json格式数据发送给服务器
         */

        // 包装service处理后的数据
        Person person = new Person();
        person.setName("李四");
        person.setAge(24);
        return person;// 框架自动将其转换为json格式发给前台浏览器
    }

    // 返回值为集合
    @RequestMapping(value = "/returnList.do", method = RequestMethod.POST)
    @ResponseBody
    public List<Person> returnList() {
        List list = new ArrayList<>();
        Person per1 = new Person();
        per1.setName("张三");
        per1.setAge(23);
        Person per2 = new Person();
        per2.setName("李四");
        per2.setAge(24);
        list.add(per1);
        list.add(per2);
        return list;
    }

    /*
        返回类型为String类型，不表示视图，只表示普通字符串
        1、怎么区分是返回视图还是普通字符串数据？
            若是控制器方法上方有@ResponseBody注解，则是返回普通字符数据；反之返回视图
        2、中文会显示乱码，需要给@RequestMapping指定一个属性produces
            属性值：text/plain;charset=utf-8;
     */

    @RequestMapping(value = "/returnString",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String returnString() {
        return "hello,打工人";
    }
}
