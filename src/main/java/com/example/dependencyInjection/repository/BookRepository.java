package com.example.dependencyInjection.repository;

import com.example.dependencyInjection.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "SELECT * FROM books b where b.genre = :genre", nativeQuery = true)
    List<Book> findAllByGenre(@Param("genre") String genre);
}
