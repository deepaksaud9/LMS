package com.LMS.service;

import com.LMS.dto.BookResponseDto;
import com.LMS.model.Book;

import java.util.List;


public interface BookService{

//    BookDTO addBook(BookDTO bookDTO);
//    BookDTO updateBook(Long id, BookDTO bookDTO);
//    void deleteBook(Long Id);
//    BookDTO getBookById(Long id);
//    List<BookDTO> getAllBooks();
//    List<BookDTO> searchBooks(String keyword);


    public BookResponseDto addBook(BookResponseDto bookDTO);
    public List<Book> getAllBooks();

    public Book getBookByIsbn(String isbn);
    public List<Book> getByTitle(String title);
    public Book updateBook( String isbn, Book book);
    public Book deleteBook(String isbn);
}
