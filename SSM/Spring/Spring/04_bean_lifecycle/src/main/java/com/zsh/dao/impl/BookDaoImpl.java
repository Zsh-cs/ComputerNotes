package com.zsh.dao.impl;

import com.zsh.dao.BookDao;

public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("book com.zsh.dao save ...");
    }

    // bean的初始化操作
    public void init(){
        System.out.println("init...");
    }

    // bean销毁前的操作
    public void destroy(){
        System.out.println("destroy...");
    }
}
