package com.zsh.dao;

import com.zsh.domain.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao {

    @Update("update accounts set money = money + #{money} where name = #{name}")
    void inMoney(@Param("name") String name,@Param("money") Double money);

    @Update("update accounts set money = money - #{money} where name = #{name}")
    void outMoney(@Param("name") String name, @Param("money") Double money);

}
