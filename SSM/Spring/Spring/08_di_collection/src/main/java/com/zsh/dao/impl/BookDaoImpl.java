package com.zsh.dao.impl;

import com.zsh.dao.BookDao;

import java.util.*;

public class BookDaoImpl implements BookDao {

    private int[] zshArray;
    private List<String> zshList;
    private Set<String> zshSet;
    private Map<String,String> zshMap;
    private Properties zshProperties;

    public void setZshArray(int[] zshArray) {
        this.zshArray = zshArray;
    }

    public void setZshList(List<String> zshList) {
        this.zshList = zshList;
    }

    public void setZshSet(Set<String> zshSet) {
        this.zshSet = zshSet;
    }

    public void setZshMap(Map<String, String> zshMap) {
        this.zshMap = zshMap;
    }

    public void setZshProperties(Properties zshProperties) {
        this.zshProperties = zshProperties;
    }

    @Override
    public void save() {
        System.out.println("book com.zsh.dao save ...");

        System.out.println("traverse Array: "+ Arrays.toString(zshArray));
        System.out.println("traverse List: "+ zshList);
        System.out.println("traverse Set: "+ zshSet);
        System.out.println("traverse Map: "+ zshMap);
        System.out.println("traverse Properties: "+ zshProperties);
    }
}
