package com.LMS.service;

import com.LMS.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService{
    public ResponseEntity<String> createBook(Book book);
    public List<Book> getAllBooks();
    public ResponseEntity<String> updateBook(Book book, int bookId);
    public String deleteBook(int bookId);
}
