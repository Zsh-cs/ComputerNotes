package com.zsh.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao {
    @Insert("insert into logs (info, createDate) values(#{info},now())")
    void log(@Param("info") String info);
}
