package com.zsh;

import com.zsh.config.SpringConfig;
import com.zsh.service.ResourceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ResourceService resourceService = context.getBean(ResourceService.class);
        boolean flag=resourceService.openURL("https://pan.baidu.com/haha","root ");
        System.out.println(flag);
    }
}
