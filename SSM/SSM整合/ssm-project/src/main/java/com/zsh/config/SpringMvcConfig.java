package com.zsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.zsh.controller", "com.zsh.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
