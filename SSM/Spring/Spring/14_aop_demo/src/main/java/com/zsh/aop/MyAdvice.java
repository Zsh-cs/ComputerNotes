package com.zsh.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect// 用于创建切面
public class MyAdvice {

    @Pointcut("execution(void com.zsh.dao.BookDao.*())")
    private void pct(){}// pct for pointcut

    @Pointcut("execution(String com.zsh.dao.BookDao.delete()))")
    private void pct2(){}

    @Before("pct()")// 前置通知
    public void before(){
        System.out.println("before advice ...");
    }

    @After("pct()")// 后置通知
    public void after(){
        System.out.println("after advice ...");
    }

    @Around("pct()")// 原始操作无返回值的环绕通知
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        pjp.proceed();// 表示对原始操作的调用
        System.out.println("around after advice ...");
    }

    @Around("pct2()")// 原始操作有返回值的环绕通知
    public Object aroundDelete(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        Object msg=pjp.proceed();// 表示对原始操作的调用
        System.out.println("around after advice ...");
        return msg;
    }

    @AfterReturning(value = "pct2()")// 原始操作正常返回后通知
    public void afterReturning(){
        System.out.println("afterReturning advice ...");
    }

    @AfterThrowing(value = "pct2()")// 抛出异常后通知
    public void afterThrowing(){
        System.out.println("afterThrowing advice ...");
    }
}
