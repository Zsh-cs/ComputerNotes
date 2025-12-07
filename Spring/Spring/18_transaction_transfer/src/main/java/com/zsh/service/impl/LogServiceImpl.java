package com.zsh.service.impl;

import com.zsh.dao.LogDao;
import com.zsh.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void log(String out, String in, Double money) {
        logDao.log(out+" transfer "+money+" into "+in);
    }
}
