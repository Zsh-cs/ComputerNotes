package com.zsh.controller;

import com.zsh.domain.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Value("${lesson}")
    private String lesson;
    @Value("${server.port}")
    private Integer port;
    @Value("${enterprise.subject[0]}")
    private String subject0;

    @Autowired
    private Environment environment;

    @Autowired
    private Enterprise enterprise;

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id){
        System.out.println("lesson: "+lesson);
        System.out.println("port: "+port);
        System.out.println("subject0: "+subject0);
        System.out.println("====================================");
        System.out.println("name: "+environment.getProperty("enterprise.name"));
        System.out.println("age: "+environment.getProperty("enterprise.age"));
        System.out.println("tel: "+environment.getProperty("enterprise.tel"));
        System.out.println("====================================");
        System.out.println("enterprise: "+enterprise);
        return "hello springboot";
    }
}
