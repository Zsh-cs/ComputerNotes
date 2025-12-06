package com.zsh.dao;

import com.zsh.domain.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao {

    @Insert("insert into accounts(name, money) values(#{name},#{money})")
    void add(Account account);

    @Delete("delete from accounts where id=#{id}")
    void deleteById(Integer id);

    @Update("update accounts set name=#{name}, money=#{money} where id=#{id}")
    void update(Account account);

    @Select("select * from accounts")
    List<Account> findAll();

    @Select("select * from accounts where id =#{id}")
    Account findById(Integer id);
}
