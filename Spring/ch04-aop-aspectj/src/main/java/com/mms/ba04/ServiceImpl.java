package com.mms.ba04;

//声明目标类
public class ServiceImpl implements IService{

    public Student doStudent(Student student) {
        System.out.println("sdoStudent...");
        return student;
    }

    @Override
    public void doSome(String name, int age) {
        System.out.println("...");
    }

    @Override
    public String doOther(String name) {
        return "张三";
    }

}
