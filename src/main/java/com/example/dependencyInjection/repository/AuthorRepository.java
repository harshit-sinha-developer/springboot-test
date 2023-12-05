package com.example.dependencyInjection.repository;

import com.example.dependencyInjection.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
