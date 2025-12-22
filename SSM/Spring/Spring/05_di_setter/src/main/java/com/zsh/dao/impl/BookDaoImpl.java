package com.zsh.dao.impl;

import com.zsh.dao.BookDao;

public class BookDaoImpl implements BookDao {
    private String databaseName;
    private int connectionNum;

    public BookDaoImpl(String databaseName, int connectionNum) {
        this.databaseName = databaseName;
        this.connectionNum = connectionNum;
    }

    @Override
    public void save() {
        System.out.println("book com.zsh.dao save to database "+databaseName+", connectionNum: "+connectionNum);
    }
}
