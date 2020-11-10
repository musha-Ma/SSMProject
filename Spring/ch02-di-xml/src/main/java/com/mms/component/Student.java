package com.mms.component;

public class Student {

    private String name;
    private int age;
    //引用类型属性
    private Address address;

    public void setName(String name) {
        System.out.println("setName...");
        this.name = name;
    }

    public void setAge(int age) {
        System.out.println("setAge...");
        this.age = age;
    }

    public void setAddress(Address address) {
        System.out.println("引用类型address的set方法执行了...");
        this.address = address;
    }

    //setXxx
    public void setGraName(String graName) {
        System.out.println("setGraName...");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
