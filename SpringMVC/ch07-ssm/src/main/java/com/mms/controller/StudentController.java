package com.mms.controller;

import com.mms.entity.Student;
import com.mms.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    // 自动注入
    @Autowired
    private StudentServiceImpl studentService;

    // 控制器方法：查询全部
    @RequestMapping(value = "/findAll")
    @ResponseBody // 响应ajax
    public List<Student> findAll() {
         return studentService.findAll();
    }
    // 控制器方法：增加学生
    @RequestMapping(value = "/addStudent")
    public ModelAndView addStudent(Student student) {
        ModelAndView mv = new ModelAndView();
        int count = studentService.addStudent(student);
        if (count > 0) {
            mv.addObject("msg","注册成功");
            mv.addObject("name",student.getStuName());
            mv.setViewName("success");
        } else {
            mv.addObject("msg","注册失败");
            mv.setViewName("fail");
        }
        return mv;
    }

}
