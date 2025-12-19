package com.zsh.controller;

import com.zsh.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody User user) {
        System.out.println("user save ... " + user);
        return "{'module':'user save'}";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id) {// @PathVariable表示从url地址中取出id这个变量的值
        System.out.println("user delete ... " + id);
        return "{'module':'user delete'}";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody User user) {
        System.out.println("user update ... " + user);
        return "{'module':'user update'}";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getById(@PathVariable Integer id) {
        System.out.println("user getById ... " + id);
        return "{'module':'user getById'}";
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getAll() {
        System.out.println("user getAll ...");
        return "{'module':'user getAll'}";
    }

}
