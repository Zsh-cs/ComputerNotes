package com.zsh;

import com.zsh.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForDIAutowire {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService=(BookService) context.getBean("bookService");
        bookService.save();
    }
}