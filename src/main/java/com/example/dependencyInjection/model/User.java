package com.example.dependencyInjection.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public User(Long id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
