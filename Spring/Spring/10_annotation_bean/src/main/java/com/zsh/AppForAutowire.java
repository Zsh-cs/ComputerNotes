package com.zsh;

import com.zsh.config.SpringConfig;
import com.zsh.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppForAutowire {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);

        BookService bookService=context.getBean(BookService.class);
        bookService.save();

    }
}
