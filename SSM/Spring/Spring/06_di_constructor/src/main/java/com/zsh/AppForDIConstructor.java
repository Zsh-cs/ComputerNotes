package com.zsh;

import com.zsh.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForDIConstructor {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContextPlus.xml");

        BookService bookService=(BookService) context.getBean("bookService");
        bookService.save();
    }
}
