package com.example.web1.controller;

import com.example.web1.mapper.BookMapper;
import com.example.web1.model.Book;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookMapper bookMapper;

    @Cacheable(value = "books")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookMapper.selectList(null);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        bookMapper.insert(book);
        return book;
    }

    @Cacheable(value = "book", key = "#id")
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookMapper.selectById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        bookMapper.updateById(book);
        return book;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookMapper.deleteById(id);
    }
}
