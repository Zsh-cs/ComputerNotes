package com.zsh.service.impl;

import com.zsh.controller.Code;
import com.zsh.dao.BookDao;
import com.zsh.domain.Book;
import com.zsh.exception.BuisnessException;
import com.zsh.exception.SystemException;
import com.zsh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public boolean save(Book book) {
        return bookDao.save(book) > 0 ? true : false;
    }

    @Override
    public boolean delete(Integer id) {
        return bookDao.delete(id) > 0 ? true : false;
    }

    @Override
    public boolean update(Book book) {
        return bookDao.update(book) > 0 ? true : false;
    }

    @Override
    public Book getById(Integer id) {
        if (id < 1) {
            throw new BuisnessException(Code.BUSINESS_ERR, "您输入的id不合法，请重新输入！");
        }

//        try {
//            int i = 1 / 0;// 模拟异常
//        } catch (Exception e) {
//            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器访问超时，请重试！", e);
//        }
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
