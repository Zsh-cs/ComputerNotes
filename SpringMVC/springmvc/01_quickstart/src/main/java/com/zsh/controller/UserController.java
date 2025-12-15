package com.zsh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 2.定义controller
@Controller// 使用@Controller来定义bean
public class UserController {

    @RequestMapping("/save")// 设置当前操作的访问路径
    @ResponseBody// 将方法返回值设置为当前操作的响应体
    public String save(){
        System.out.println("user save ...");
        return "{'module':'springmvc'}";
    }
}
