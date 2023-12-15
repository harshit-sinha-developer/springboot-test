package com.example.dependencyInjection.controllers;

import com.example.dependencyInjection.model.BookInventory;
import com.example.dependencyInjection.model.BorrowInfo;
import com.example.dependencyInjection.repository.BookInventoryRepository;
import com.example.dependencyInjection.repository.BorrowInfoRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BorrowController {
    @Autowired
    private BorrowInfoRepository borrowInfoRepository;

    @Autowired
    private BookInventoryRepository bookInventoryRepository;

    @PostMapping("/borrow/{userId}/book/{bookId}")
    public Boolean borrow(@PathVariable Long userId, @PathVariable Long bookId, HttpServletResponse response) {
        Optional<BookInventory> optionalBookInventory = bookInventoryRepository.findByBookId(bookId);

        if(optionalBookInventory.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return false;
        }

        BookInventory bookInventory = optionalBookInventory.get();

        if(bookInventory.getAvailableCount() <= 0) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return false;
        }

        bookInventory.setAvailableCount(bookInventory.getAvailableCount() - 1);

        BorrowInfo borrowInfo = new BorrowInfo();
        borrowInfo.setBookId(bookId);
        borrowInfo.setUserId(userId);
        borrowInfo.setStatus("BORROWED");

        bookInventoryRepository.save(bookInventory);
        borrowInfoRepository.save(borrowInfo);
        return true;
    }

    @PostMapping("/return-book/{userId}/book/{bookId}")
    public Boolean returnBook(@PathVariable Long userId, @PathVariable Long bookId, HttpServletResponse response) {
        Optional<BorrowInfo> optionalBorrowInfo = borrowInfoRepository.findByUserIdAndBookIdAndStatus(userId, bookId, "BORROWED");

        if(optionalBorrowInfo.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return false;
        }

        Optional<BookInventory> optionalBookInventory = bookInventoryRepository.findByBookId(bookId);

        if(optionalBookInventory.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return false;
        }

        BookInventory bookInventory = optionalBookInventory.get();


        if(bookInventory.getAvailableCount().longValue() == bookInventory.getTotalCount().longValue()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return false;
        }

        bookInventory.setAvailableCount(bookInventory.getAvailableCount() + 1);

        bookInventoryRepository.save(bookInventory);

        BorrowInfo borrowInfo = optionalBorrowInfo.get();
        borrowInfo.setStatus("RETURNED");
        borrowInfoRepository.save(borrowInfo);

        return true;
    }
}
