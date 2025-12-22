package com.zsh.controller;

import com.zsh.domain.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController// 等价于@Controller + @ResponseBody
@RequestMapping("/books")
public class BookController {
    private List<Book> books=new ArrayList<>();

    @PostMapping
    public String save(@RequestBody Book book) {
        System.out.println("book save ==> " + book);
        books.add(book);
        return "{'module':'book save successfully'}";
    }

    @GetMapping
    public List<Book> getAll() {
        System.out.println("book getAll is running ...");

        return books;
    }
}
