package com.zsh;

import com.zsh.dao.BookDao;
import com.zsh.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        BookDao bookDao=(BookDao) context.getBean("bookDao");
        System.out.println(bookDao);
        BookService bookService=context.getBean(BookService.class);
        System.out.println(bookService);
    }
}