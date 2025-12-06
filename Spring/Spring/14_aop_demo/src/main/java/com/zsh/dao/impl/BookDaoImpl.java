package com.zsh.dao.impl;

import com.zsh.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println(System.currentTimeMillis());
        System.out.println("book dao save ...");

//        Long startTime=System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            System.out.println("book dao save ...");
//        }
//        Long endTime=System.currentTimeMillis();
//        Long time=endTime-startTime;
//        System.out.println("执行10000次save操作所消耗的时间："+time+" ms");
    }

    @Override
    public void update() {
        System.out.println("book dao update ...");
    }

    @Override
    public void delete() {
        System.out.println("book dao delete ...");
    }

    @Override
    public void select() {
        System.out.println("book dao select ...");
    }
}
