package com.zsh.service;

import com.zsh.domain.Account;

import java.util.List;

public interface AccountService {
    void add(Account account);
    void deleteById(Integer id);
    void update(Account account);
    List<Account> findAll();
    Account findById(Integer id);

}
