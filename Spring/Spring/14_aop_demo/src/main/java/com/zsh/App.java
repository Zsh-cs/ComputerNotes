package com.zsh;

import com.zsh.config.SpringConfig;
import com.zsh.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);

        BookDao bookDao=context.getBean(BookDao.class);
        bookDao.update();
    }
}