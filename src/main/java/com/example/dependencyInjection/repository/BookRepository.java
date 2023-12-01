package com.example.dependencyInjection.repository;

import com.example.dependencyInjection.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
