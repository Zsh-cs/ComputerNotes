package com.zsh.controller;

import com.zsh.domain.Book;
import org.springframework.web.bind.annotation.*;

@RestController// 等价于@Controller+@ResponseBody
@RequestMapping("/books")
public class BookController {

//    @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public String save(@RequestBody Book book) {
        System.out.println("book save ... " + book);
        return "{'module':'book save'}";
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {// @PathVariable表示从url地址中取出id这个变量的值
        System.out.println("book delete ... " + id);
        return "{'module':'book delete'}";
    }

//    @RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public String update(@RequestBody Book book) {
        System.out.println("book update ... " + book);
        return "{'module':'book update'}";
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id) {
        System.out.println("book getById ... " + id);
        return "{'module':'book getById'}";
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getAll() {
        System.out.println("book getAll ...");
        return "{'module':'book getAll'}";
    }
}
