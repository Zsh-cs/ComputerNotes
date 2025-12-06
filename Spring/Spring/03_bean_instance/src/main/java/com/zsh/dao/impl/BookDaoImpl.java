package com.zsh.dao.impl;

import com.zsh.dao.BookDao;

public class BookDaoImpl implements BookDao {

    // IoC容器使用反射获取私有无参构造器，并调用它创建bean
    //Caution: Spring无法调用有参构造器
    private BookDaoImpl(){
        System.out.println("book com.zsh.dao constructor is running ...");
    }

    @Override
    public void save() {
        System.out.println("book com.zsh.dao save ...");
    }
}
