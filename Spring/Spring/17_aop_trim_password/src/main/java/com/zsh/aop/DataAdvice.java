package com.zsh.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataAdvice {

    @Pointcut("execution(boolean com.zsh.service.*Service.openURL(..))")
    private void servicePct(){};

    @Around("servicePct()")
    public Object trimPassword(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        // 遍历修改参数
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                args[i] = args[i].toString().trim();
            }
        }

        // 传入修改后的参数
        Object ret=pjp.proceed(args);
        return ret;
    }
}
