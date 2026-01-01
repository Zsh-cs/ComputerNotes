package com.zsh.query;

import com.zsh.domain.User;
import lombok.Data;

@Data
public class UserQuery extends User {
    private Integer maxAge;
}
