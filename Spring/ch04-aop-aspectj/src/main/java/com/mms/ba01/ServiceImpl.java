package com.mms.ba01;

//声明目标类
public class ServiceImpl implements IService {
    @Override
    public void doSome(String name, int age) {
        System.out.println("===doSome()===");
    }
}
