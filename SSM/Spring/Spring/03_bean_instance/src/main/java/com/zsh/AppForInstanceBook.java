package com.zsh;

import com.zsh.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForInstanceBook {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        BookDao bookDao=(BookDao) context.getBean("bookDao");
        bookDao.save();
    }
}
