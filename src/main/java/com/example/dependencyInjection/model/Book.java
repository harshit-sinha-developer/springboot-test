package com.example.dependencyInjection.model;

import jakarta.persistence.*;

@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Float price;
    private String genre;

    private Long authorId;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Book(Long id, String title, Float price, String genre) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.genre = genre;
    }

    public Book() {
    }

    public Book(String title, Float price, String genre) {
        this.title = title;
        this.price = price;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
