package com.example.dependencyInjection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "book_inventory")
public class BookInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long bookId;

    private Integer availableCount;

    private Integer totalCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
