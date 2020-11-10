package com.mms.vo;

//接收数据的普通类
public class Person {
    private String name;
    private int age;

    public Person() {
        System.out.println("无参构造...");
    }

    public void setName(String name) {
        System.out.println("setName...");
        this.name = name;
    }

    public void setAge(int age) {
        System.out.println("setAge...");
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
