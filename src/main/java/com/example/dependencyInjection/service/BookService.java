package com.example.dependencyInjection.service;

import com.example.dependencyInjection.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired JdbcTemplate jdbcTemplate;

    public Book findById(Long bookId) {
        return jdbcTemplate.query(
                "SELECT id, title, price, genre from books where id = ?",
                (rs, rowNum) -> new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getFloat("price"),
                        rs.getString("genre")
                ),
                bookId
                ).stream().findFirst().get();
    }

    public void create(String title, Float price, String genre) {
        jdbcTemplate.update("INSERT INTO books (title, price, genre) VALUES (?, ?, ?)", title, price, genre);
    }
}
