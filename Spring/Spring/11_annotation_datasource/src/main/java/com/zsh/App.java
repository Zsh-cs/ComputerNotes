package com.zsh;

import com.zsh.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =new AnnotationConfigApplicationContext(SpringConfig.class);

        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource);
    }
}
