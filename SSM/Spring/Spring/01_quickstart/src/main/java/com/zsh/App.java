package com.zsh;

import com.zsh.service.BookService;
import com.zsh.service.impl.BookServiceImpl;

public class App {
    public static void main(String[] args) {
        BookService bookService=new BookServiceImpl();
        bookService.save();
    }
}
