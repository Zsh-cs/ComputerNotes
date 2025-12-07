package com.zsh.dao.impl;

import com.zsh.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    @Override
    public String findName(int id, String password) {
        System.out.println("id: "+id+", password: "+password);
        // int i=1/0;// 除零异常
        return "zsh";
    }
}
