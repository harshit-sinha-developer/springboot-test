package com.example.dependencyInjection.model;

import jakarta.persistence.Entity;

public class Book {
    private Long id;
    private String title;
    private Float price;
    private String genre;

    public Book(Long id, String title, Float price, String genre) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.genre = genre;
    }

    public Book() {
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
