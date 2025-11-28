package com.zsh.factory;

import com.zsh.dao.OrderDao;
import com.zsh.dao.impl.OrderDaoImpl;

public class OrderDaoFactory {
    public static OrderDao getOrderDao(){
        System.out.println("order dao factory setup ...");
        return new OrderDaoImpl();
    }
}
