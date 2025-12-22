package com.zsh.service;

import com.zsh.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BookService {
    boolean save(Book book);
    boolean delete(Integer id);
    boolean update(Book book);
    Book getById(Integer id);
    List<Book> getAll();
}
