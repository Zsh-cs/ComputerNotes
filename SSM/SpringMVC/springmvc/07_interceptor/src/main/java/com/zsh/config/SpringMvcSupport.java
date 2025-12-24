package com.zsh.config;

import com.zsh.controller.interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {

    @Autowired
    private ProjectInterceptor interceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 访问/books及其子目录，都需要经过拦截器
        registry.addInterceptor(interceptor).addPathPatterns("/books", "/books/*");
    }
}
