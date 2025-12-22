package com.zsh.service.impl;

import com.zsh.dao.AccountDao;
import com.zsh.domain.Account;
import com.zsh.service.AccountService;
import com.zsh.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private LogService logService;

    @Override
    public void transfer(String out, String in, Double money) {
        try{
            accountDao.outMoney(out,money);
            int i=1/0;// 模拟数据库突然崩溃
            accountDao.inMoney(in,money);
        }finally {
            logService.log(out,in,money);
        }

    }
}
