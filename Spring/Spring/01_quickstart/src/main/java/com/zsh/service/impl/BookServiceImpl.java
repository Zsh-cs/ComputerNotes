package com.zsh.service.impl;

import com.zsh.dao.BookDao;
import com.zsh.service.BookService;

public class BookServiceImpl implements BookService {
    // 删除业务层中使用new方式创建的dao对象
    // private BookDao bookDao=new BookDaoImpl();

    private BookDao bookDao;

    // 改为提供对应的setter，由容器调用此setter来实例化dao对象
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
}