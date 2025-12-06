package com.zsh;

import com.zsh.config.SpringConfig;
import com.zsh.domain.Account;
import com.zsh.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App2 {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);

        AccountService accountService = context.getBean(AccountService.class);
        Account account=accountService.findById(1);
        System.out.println(account);
    }
}
