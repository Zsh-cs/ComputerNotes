package com.zsh;

import com.zsh.dao.BookDao;
import com.zsh.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForLifeCycle {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        context.registerShutdownHook();// 注册关闭钩子，建议采用此方法关闭，也可以放在代码末尾

        BookDao bookDao=(BookDao) context.getBean("bookDao");
        bookDao.save();
        // context.close();// 此方法比较暴力

    }
}