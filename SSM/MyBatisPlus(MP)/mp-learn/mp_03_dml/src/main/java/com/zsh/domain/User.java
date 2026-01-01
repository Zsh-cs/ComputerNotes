package com.zsh.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String tel;

    public User(String name, String password, Integer age, String tel) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.tel = tel;
    }

}
