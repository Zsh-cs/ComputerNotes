package com.zsh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MyAdvice {

    @Pointcut("execution(* com.zsh.dao.BookDao.findName(..))")
    private void pct(){};

    @Before("pct()")
    public void before(JoinPoint jp){
        Object[] args = jp.getArgs();
        // System.out.println(Arrays.toString(args));
        // System.out.println("before advice ...");
    }

    @After("pct()")
    public void after(JoinPoint jp){
        // System.out.println("after advice ...");
    }

    @Around("pct()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args= pjp.getArgs();
        args[0]=666;

        System.out.println("around before advice ...");
        Object ret=pjp.proceed(args);// 有参重载方法：Object proceed(Object[] var1) throws Throwable;
        System.out.println("around after advice ...");
        return ret;
    }

    @AfterReturning(value = "pct()",returning = "ret")// 指定ret用于接收原始方法的返回值
    public void afterReturning(Object ret){
        System.out.println("afterReturning advice ...");
        System.out.println("ret: "+ret);
    }

    @AfterThrowing(value = "pct()",throwing = "t")// 指定t用于接收原始方法的异常
    public void afterThrowing(Throwable t){
        System.out.println("afterThrowing advice ...");
        System.out.println("exception: "+t);
    }

}
