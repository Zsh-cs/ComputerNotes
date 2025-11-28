package com.zsh;

import com.zsh.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForScope {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService1=(BookService) context.getBean("service");
        BookService bookService2=(BookService) context.getBean("service");
        System.out.println(bookService1);
        System.out.println(bookService2);

    }
}