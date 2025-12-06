package com.zsh.dao.impl;

import com.zsh.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void save() {
        System.out.println("order com.zsh.dao save ...");
    }
}
