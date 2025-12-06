package com.zsh.dao.impl;

import com.zsh.dao.BookDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Repository("bookDao")
@Scope("singleton")// 单例
public class BookDaoImpl implements BookDao {

    @Value("${name}")
    private String name;

    @Override
    public void save() {
        System.out.println("book com.zsh.dao save ... 1 "+name);
    }

    @PostConstruct
    public void init(){
        System.out.println("init ...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy ...");
    }
}
