package com.zsh.service.impl;

import com.zsh.dao.ResourceDao;
import com.zsh.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public boolean openURL(String url, String password) {
        return resourceDao.readResource(url,password);
    }
}
