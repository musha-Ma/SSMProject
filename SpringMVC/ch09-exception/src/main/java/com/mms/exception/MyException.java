package com.mms.exception;

// 自定义异常类
public class MyException extends Exception{

    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }
}
