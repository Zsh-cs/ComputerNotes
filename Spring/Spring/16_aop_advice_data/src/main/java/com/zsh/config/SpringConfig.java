package com.zsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.zsh")
@EnableAspectJAutoProxy// 告诉Spring程序中存在用注解开发的AOP
public class SpringConfig {
}
