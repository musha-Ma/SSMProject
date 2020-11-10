package com.mms.service.impl;

import com.mms.service.SomeService;

public class SomeServiceImpl implements SomeService {
    //无参构造
    public SomeServiceImpl() {
        System.out.println("SomeServiceImpl类的无参构造执行了...");
    }

    @Override
    public void doSome() {
        System.out.println("执行了SomeServiceImpl的doSome()方法");
    }
}
