package com.zsh.dao.impl;

import com.zsh.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println("book dao save ...");
    }

    @Override
    public void update() {
        System.out.println("book dao update ...");
    }

    @Override
    public String delete() {
        System.out.println("book dao delete ...");
        return "delete successfully!";
    }

    @Override
    public void select() {
        System.out.println("book dao select ...");
    }
}
