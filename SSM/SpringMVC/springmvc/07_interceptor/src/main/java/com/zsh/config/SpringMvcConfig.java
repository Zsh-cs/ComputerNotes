package com.zsh.config;

import com.zsh.controller.interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@ComponentScan({"com.zsh.controller", "com.zsh.config"})
@ComponentScan({"com.zsh.controller"})
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ProjectInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 访问/books及其子目录，都需要经过拦截器
        registry.addInterceptor(interceptor).addPathPatterns("/books", "/books/*");
    }

}
