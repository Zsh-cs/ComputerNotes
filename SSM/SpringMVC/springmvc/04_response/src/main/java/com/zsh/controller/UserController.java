package com.zsh.controller;

import com.zsh.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    // 响应页面/跳转页面
    @RequestMapping("/toJumpPage")
    public String toJumpPage(){
        System.out.println("jump page");
        return "page.jsp";
    }

    // 响应文本数据
    @RequestMapping("/toText")
    @ResponseBody
    public String toText(){
        System.out.println("return text");
        return "response text";
    }

    // 响应POJO：json
    @RequestMapping("/toJsonPojo")
    @ResponseBody
    public User toJsonPojo(){
        System.out.println("return json pojo");
        User user=new User("zsh",999);
        return user;
    }

    // 响应POJO集合：json
    @RequestMapping("/toJsonPojoList")
    @ResponseBody
    public List<User> toJsonPojoList(){
        System.out.println("return json pojo list");
        User user1=new User("aj",6);
        User user2=new User("swift",52);
        List<User> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return users;
    }
}
