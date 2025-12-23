package com.zsh.dao;

import com.zsh.domain.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {

    @Insert("insert into books (type, name, description) value (#{type},#{name},#{description})")
    int save(Book book);

    @Delete("delete from books where id=#{id}")
    int delete(Integer id);

    @Update("update books set type=#{type},name=#{name},description=#{description} where id=#{id}")
    int update(Book book);

    @Select("select * from books where id=#{id}")
    Book getById(Integer id);

    @Select("select * from books")
    List<Book> getAll();
}
