package com.zsh.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

// 4.定义一个Servlet容器启动的配置类，在里面加载SpringMVC的配置
public class ServletContainerInitConfig extends AbstractDispatcherServletInitializer{
    @Override
    // 加载SpringMVC容器配置
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
        context.register(SpringMvcConfig.class);
        return context;
    }

    @Override
    // 设置哪些请求交由SpringMVC处理
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    // 加载Spring容器配置
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
