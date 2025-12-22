package com.zsh.service.impl;

import com.zsh.dao.BookDao;
import com.zsh.service.BookService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BookServiceImpl implements BookService, InitializingBean, DisposableBean {

    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
        System.out.println("properties set");
    }

    @Override
    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }

    @Override
    // 属性设置完毕后，才运行此方法
    public void afterPropertiesSet() throws Exception {
        System.out.println("service init");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("service destroy");
    }
}