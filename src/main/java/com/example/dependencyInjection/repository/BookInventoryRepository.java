package com.example.dependencyInjection.repository;

import com.example.dependencyInjection.model.BookInventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookInventoryRepository extends CrudRepository<BookInventory, Long> {
    @Query(value = "SELECT * FROM book_inventory bi where bi.id = :id AND bi.book_id = :bookId", nativeQuery = true)
    Optional<BookInventory> findByIdAndBookId(@Param("id") Long id, @Param("bookId") Long bookId);

    @Query(value = "SELECT * FROM book_inventory bi where bi.book_id = :bookId", nativeQuery = true)
    Optional<BookInventory> findByBookId(@Param("bookId") Long bookId);
}
