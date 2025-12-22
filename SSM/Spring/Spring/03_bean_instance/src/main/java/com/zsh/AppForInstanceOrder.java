package com.zsh;

import com.zsh.dao.OrderDao;
import com.zsh.factory.OrderDaoFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForInstanceOrder {
    public static void main(String[] args) {
        // 原始方式：通过静态工厂创建对象
        // OrderDao orderDao= OrderDaoFactory.getOrderDao();
        // orderDao.save();

        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        OrderDao orderDao=(OrderDao) context.getBean("orderDao");
        orderDao.save();
    }
}
