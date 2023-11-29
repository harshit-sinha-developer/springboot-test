package com.example.dependencyInjection.controllers;

import com.example.dependencyInjection.model.Book;
import com.example.dependencyInjection.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = {"/book/"})
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("{bookId}")
    public Book getBook(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }

    @GetMapping("/")
    public ArrayList<Book> getBooks() {
        return new ArrayList<>();
    }

    @PostMapping("/")
    public Book createBook(@RequestBody Book bookRequest) {
        return new Book();
    }

    @DeleteMapping("{bookId}")
    public void deleteBook(@PathVariable Long bookId) {

    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public void updateBook(@PathVariable Long bookId, @RequestBody Book bookRequest) {

    }
}
