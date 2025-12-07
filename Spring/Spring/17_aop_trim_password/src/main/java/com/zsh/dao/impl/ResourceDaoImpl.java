package com.zsh.dao.impl;

import com.zsh.dao.ResourceDao;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceDaoImpl implements ResourceDao {
    @Override
    public boolean readResource(String url, String password) {
        return password.equals("root");
    }
}
