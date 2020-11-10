package com.mms.ba04;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

//声明切面类
@Aspect
public class MyAspectJ {

    /*
        环绕通知：@Around(切入点表达式)
        1、环绕通知是最重要的一个通知，他表示在连接点方法的前或者后都可以执行，它的本质就是jdk动态代理的invoke
           方法的method参数
        2、定义格式
            a、public
            b、必须有返回值，类型为Object

     */
    @Around(value = "execution(* *.doStudent(..))")

    /*
        再次回忆一下jdk动态代理的invoke方法的定义
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        是不是感觉与下面的方法定义眼熟啊，没错，环绕通知切面的定义实质上就是jdk动态代理
     */
    public Object around(ProceedingJoinPoint pj) throws Throwable {
        System.out.println("环绕通知...");
        Object result = null;
        result = pj.proceed();
        Student student = new Student();
        student.setName("李四");
        student.setAge(24);
        //改变连接点方法返回值
        result = student;
        System.out.println("事务已提交...");
        return result;
    }
}
