package com.zsh.service.impl;

import com.zsh.dao.BookDao;
import com.zsh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired// 此注解必须写！
    @Qualifier("bookDao")// 指定要注入的bean的名称
    private BookDao bookDao;

    // 不需要再写setter了，@Autowired注解会进行暴力反射
//    public void setBookDao(BookDao bookDao) {
//        this.bookDao = bookDao;
//    }

    @Override
    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
}