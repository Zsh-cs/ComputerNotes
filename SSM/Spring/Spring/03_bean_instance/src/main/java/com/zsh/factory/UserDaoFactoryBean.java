package com.zsh.factory;

import com.zsh.dao.UserDao;
import com.zsh.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.FactoryBean;

public class UserDaoFactoryBean implements FactoryBean<UserDao> {
    @Override
    //Logic: 代替原始实例工厂中创建对象的方法
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    @Override
    //Logic: 指定所要创建的对象的类型
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    //Logic: 指定所要创建的对象是否为单例
    public boolean isSingleton() {
        return true;
    }
}
