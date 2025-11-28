package com.zsh.dao.impl;

import com.zsh.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("user dao save ...");
    }
}
