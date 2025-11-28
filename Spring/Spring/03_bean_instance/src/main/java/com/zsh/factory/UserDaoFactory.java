package com.zsh.factory;

import com.zsh.dao.UserDao;
import com.zsh.dao.impl.UserDaoImpl;

public class UserDaoFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
