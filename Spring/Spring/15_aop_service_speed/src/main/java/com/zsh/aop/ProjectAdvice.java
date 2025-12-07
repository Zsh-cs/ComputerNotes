package com.zsh.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProjectAdvice {

    @Pointcut("execution(* com.zsh.service.*Service.*(..))")
    private void servicePct(){};

    @Around("servicePct()")
    public Object calculateServiceSpeed(ProceedingJoinPoint pjp) throws Throwable {
        long startTime=System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            pjp.proceed();
        }
        long endTime=System.currentTimeMillis();
        long elapsedTime=endTime-startTime;
        Signature signature=pjp.getSignature();
        System.out.println("执行千次方法" + signature + "，耗时：" + elapsedTime + " ms");
        return pjp.proceed();// 此处需返回原始方法的返回值，否则容易报错
    }
}
