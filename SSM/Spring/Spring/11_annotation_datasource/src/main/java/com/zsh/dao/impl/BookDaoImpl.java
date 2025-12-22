package com.zsh.dao.impl;

import com.zsh.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println("book com.zsh.dao save ...");
    }

}
