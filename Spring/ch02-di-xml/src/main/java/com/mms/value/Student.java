package com.mms.value;

public class Student {

    private String name;
    private int age;
    //引用类型属性
    private Address address;

    //有参构造
    public Student(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
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
