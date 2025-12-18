package com.zsh.controller;

import com.zsh.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    // 普通参数
    @RequestMapping("/commonParam")
    @ResponseBody
    public String commonParam(String name, int age){
        System.out.println("common param: name ==> "+name);
        System.out.println("common param: age ==> "+age);
        return "{'module':'common param'}";
    }

    // 普通参数：请求参数名与形参名不一致的情况
    @RequestMapping("/commonParamDifferentName")
    @ResponseBody
    public String commonParamDifferentName(@RequestParam("name") String userName, int age){
        System.out.println("common param: name ==> "+userName);
        System.out.println("common param: age ==> "+age);
        return "{'module':'common param different name'}";
    }

    // POJO参数
    @RequestMapping("/pojoParam")
    @ResponseBody
    public String pojoParam(User user){
        System.out.println("pojo param: user ==> "+user);
        return "{'module':'pojo param'}";
    }

    // 嵌套POJO参数
    @RequestMapping("/pojoContainsPojoParam")
    @ResponseBody
    public String pojoContainsPojoParam(User user){
        System.out.println("pojo param: user ==> "+user);
        return "{'module':'pojo contains pojo param'}";
    }

    // 数组参数
    @RequestMapping("/arrayParam")
    @ResponseBody
    public String arrayParam(String[] hobbies){
        System.out.println("array param: hobbies ==> "+ Arrays.toString(hobbies));
        return "{'module':'array param'}";
    }

    // 集合参数
    @RequestMapping("/listParam")
    @ResponseBody
    public String listParam(@RequestParam List<String> hobbies){// 此处必须加@RequestParam注解来绑定参数关系，否则报错
        System.out.println("list param: hobbies ==> "+ hobbies);
        return "{'module':'list param'}";
    }

    // 集合参数：json
    @RequestMapping("/listParamForJson")
    @ResponseBody
    public String listParamForJson(@RequestBody List<String> hobbies){// 此处必须加@RequestBody注解来绑定参数关系
        System.out.println("list param json: hobbies ==> " + hobbies);
        return "{'module':'list param for json'}";
    }

    // POJO参数：json
    @RequestMapping("/pojoParamForJson")
    @ResponseBody
    public String pojoParamForJson(@RequestBody User user){// 此处必须加@RequestBody注解来绑定参数关系
        System.out.println("pojo param json: user ==> " + user);
        return "{'module':'pojo param for json'}";
    }

    // POJO集合参数：json
    @RequestMapping("/pojoListParamForJson")
    @ResponseBody
    public String pojoListParamForJson(@RequestBody List<User> users){// 此处必须加@RequestBody注解来绑定参数关系
        System.out.println("pojo list param json: users ==> " + users);
        return "{'module':'pojo list param for json'}";
    }

    // 日期参数
    @RequestMapping("/dateParam")
    @ResponseBody
    public String dateParam(
            Date date1,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date3
    ){
        System.out.println("date param: date1 ==> "+date1);
        System.out.println("date param: date2(yyyy-MM-dd) ==> "+date2);
        System.out.println("date param: date3(yyyy-MM-dd HH:mm:ss) ==> "+date3);
        return "{'module':'date param'}";
    }
}
