package com.example.dependencyInjection.repository;

import com.example.dependencyInjection.model.BorrowInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BorrowInfoRepository extends CrudRepository<BorrowInfo, Long> {
    @Query(value = "SELECT * FROM borrow_info bi where bi.user_id = :userId AND bi.book_id = :bookId AND bi.status = :status LIMIT 1", nativeQuery = true)
    Optional<BorrowInfo> findByUserIdAndBookIdAndStatus(@Param("userId") Long userId, @Param("bookId") Long bookId, @Param("status") String status);
}
