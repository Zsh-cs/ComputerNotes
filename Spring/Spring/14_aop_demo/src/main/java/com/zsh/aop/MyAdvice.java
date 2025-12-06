package com.zsh.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect// 用于创建切面
public class MyAdvice {

    @Pointcut("execution(void com.zsh.dao.BookDao.update())")
    private void pointcutMethod(){}

    @Before("pointcutMethod()")
    public void before(){
        System.out.println(System.currentTimeMillis());
    }
}
