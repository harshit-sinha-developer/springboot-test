package com.example.dependencyInjection.controllers;

import com.example.dependencyInjection.exception.NotFoundException;
import com.example.dependencyInjection.model.Book;
import com.example.dependencyInjection.model.BookInventory;
import com.example.dependencyInjection.repository.BookInventoryRepository;
import com.example.dependencyInjection.repository.BookRepository;
import com.example.dependencyInjection.service.BookService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/book"})
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookInventoryRepository bookInventoryRepository;

    @GetMapping("/{bookId}")
    public Book getBook(@PathVariable Long bookId, HttpServletResponse response) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty()) {
            throw new NotFoundException("getBookId", "Book not found.");
        }

        return optionalBook.get();
    }

    @GetMapping("")
    public List<Book> getBooks(@RequestParam @Nullable String genre) {
            ArrayList<Book> books = new ArrayList<>();

        if(genre != null) {
            return bookRepository.findAllByGenre(genre);
        }

        for(Book book: bookRepository.findAll()) {
            books.add(book);
        }
        return books;
    }

    @PostMapping("")
    public Book createBook(@RequestBody Book bookRequest) {
        Book book = new Book(bookRequest.getTitle(), bookRequest.getPrice(), bookRequest.getGenre());
        return bookRepository.save(book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT}, path = "/{bookId}")
    public void updateBook(@PathVariable Long bookId, @RequestBody Book bookRequest, HttpServletResponse response) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty()) {
            throw new NotFoundException("updateBookId", "Book not found for updation.");
        }

        Book book = optionalBook.get();
        if(bookRequest.getPrice() != null) {
            book.setPrice(bookRequest.getPrice());
        }

        if(bookRequest.getTitle() != null) {
            book.setTitle(bookRequest.getTitle());
        }

        if(bookRequest.getGenre() != null) {
            book.setGenre(bookRequest.getGenre());
        }

        if(bookRequest.getAuthorId() != null) {
            book.setAuthorId(bookRequest.getAuthorId());
        }

        bookRepository.save(book);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

    @PostMapping("/{bookId}/inventory")
    public BookInventory createBookInventory(@PathVariable Long bookId, @RequestBody BookInventory bookInventory) {
        bookInventory.setBookId(bookId);
        return bookInventoryRepository.save(bookInventory);
    }

    @GetMapping("/{bookId}/inventory/{bookInventoryId}")
    public Optional<BookInventory> findBookInventory(@PathVariable Long bookId, @PathVariable Long bookInventoryId) {
        return bookInventoryRepository.findByIdAndBookId(bookInventoryId, bookId);
    }
}
