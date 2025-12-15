package com.zsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 3.创建SpringMVC的配置文件，加载Controller对应的bean
@Configuration
@ComponentScan("com.zsh.controller")
public class SpringMvcConfig {
}
