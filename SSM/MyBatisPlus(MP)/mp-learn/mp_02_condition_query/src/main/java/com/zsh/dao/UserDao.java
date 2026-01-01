package com.zsh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("select count(*) as count, tel from user group by tel ")
    List<Map<String, User>> getTelGroup();
}
