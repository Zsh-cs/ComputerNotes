package com.zsh.service;

import com.zsh.domain.Account;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional(rollbackFor = IOException.class)// 开启事务
public interface AccountService {
    /**
     *
     * @param out 转出账户
     * @param in 转入账户
     * @param money 转账金额
     */
    public void transfer(String out,String in,Double money);

}
