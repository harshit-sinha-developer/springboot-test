package com.example.dependencyInjection.controllers;

import com.example.dependencyInjection.model.Author;
import com.example.dependencyInjection.model.Book;
import com.example.dependencyInjection.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = {"/author/"})
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/")
    public void create(@RequestBody Author author) {
        authorRepository.save(author);
    }

    @GetMapping("/")
    public List<Author> getAll() {
        ArrayList<Author> authors = new ArrayList<>();
        for(Author author: authorRepository.findAll()) {
            authors.add(author);
        }
        return authors;
    }
}
