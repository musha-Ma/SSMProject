package com.mms.servlet;

import com.mms.entity.Student;
import com.mms.service.IStudentService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySpringServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //接收参数
        int stuNo = Integer.parseInt(request.getParameter("stuno"));
        String stuName = request.getParameter("stuname");
        int cardId = Integer.parseInt(request.getParameter("cardid"));
        int classId = Integer.parseInt(request.getParameter("classid"));

        /*实例化ioc容器
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        System.out.println("ioc容器--->"+ac);
        */

        //使用监听器怎么获取容器对象
        WebApplicationContext ac = null;
        //获取servlet上下文对象
        ServletContext servletContext = getServletContext();
        //使用sprng工具类获取ioc容器对象
        ac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        System.out.println("ioc容器--->"+ac);
        //获取service对象
        IStudentService service = (IStudentService) ac.getBean("studentServiceImpl");
        Student student = new Student();
        student.setStuNo(stuNo);
        student.setStuName(stuName);
        student.setCardID(cardId);
        student.setClassID(classId);
        service.addStudent(student);

        request.getRequestDispatcher("result.jsp").forward(request,response);
    }
}
