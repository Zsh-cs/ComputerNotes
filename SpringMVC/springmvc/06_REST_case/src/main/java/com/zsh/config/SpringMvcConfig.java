package com.zsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.zsh.controller","com.zsh.config"})
@EnableWebMvc// 其中一个功能：对json数据进行自动类型转换
public class SpringMvcConfig {
}
