package com.zsh;

import com.zsh.dao.BookDao;
import com.zsh.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App2 {
    public static void main(String[] args) {
        // 获取IoC容器
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        // 传入bean的id来获取bean
        BookDao bookDao=(BookDao) context.getBean("bookDao");
        BookService bookService=(BookService) context.getBean("bookService");
        bookService.save();
    }
}
