package com.zsh;

import com.zsh.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForInstanceUser {
    public static void main(String[] args) {
        // 原始方式：通过实例工厂创建对象
        // UserDaoFactory factory=new UserDaoFactory();
        // UserDao userDao=factory.getUserDao();
        // userDao.save();

        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDao userDao=(UserDao) context.getBean("userDao");
        userDao.save();

    }
}
