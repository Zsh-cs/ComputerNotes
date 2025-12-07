package com.zsh.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface LogService {
    @Transactional(propagation = Propagation.REQUIRES_NEW)// 独立事务，不受事务管理员管控
    void log(String out,String in,Double money);
}
