package com.zsh.domain;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String tel;

    public User() {

    }

    public User(String name, String password, Integer age, String tel) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.tel = tel;
    }

}
