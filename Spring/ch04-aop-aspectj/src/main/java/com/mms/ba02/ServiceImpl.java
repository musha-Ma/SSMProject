package com.mms.ba02;

//声明目标类
public class ServiceImpl implements IService {
    @Override
    public void doSome(String name, int age) {
        System.out.println("doSome...");
    }

    @Override
    public String doOther(String name) {
        System.out.println("doOther...");
        return name;
    }

    @Override
    public Student doStudent(Student student) {
        return student;
    }
}
