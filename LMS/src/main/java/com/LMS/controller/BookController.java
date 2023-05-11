package com.LMS.controller;

import com.LMS.dto.BookResponseDto;
import com.LMS.model.Book;
import com.LMS.response.Response;
import com.LMS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    //--------------------------------- Create Book -----------------------------------
    @PostMapping("/save")
    public ResponseEntity<Response> saveBok(@RequestBody BookResponseDto bookDTO){
        BookResponseDto addBook = bookService.addBook(bookDTO);
        Response<BookResponseDto> bookResponse = new Response<>(HttpStatus.ACCEPTED, addBook);

        return new ResponseEntity<Response>(bookResponse,HttpStatus.CREATED);
    }
    //--------------------------------- Get All Book -----------------------------------
    @GetMapping("/getAll")
    public ResponseEntity<Response> getBooks(){
        List<Book> books = bookService.getAllBooks();
        Response<List> bookResponse = new Response<>(HttpStatus.OK, books);

        return new ResponseEntity<Response>(bookResponse,HttpStatus.OK);
    }
    //--------------------------------- Get Book By (Id) -----------------------------------
    @GetMapping("/get/isbn={isbn}")
    public ResponseEntity<Response> getBookById(@PathVariable String isbn){
        Book book = bookService.getBookByIsbn(isbn);
        Response<Book> responseBook = new Response<>(HttpStatus.OK, book);

        return new ResponseEntity<>(responseBook,HttpStatus.OK);
    }
    //--------------------------------- Get Book By (Name) -----------------------------------
    @GetMapping("/get/title={title}")
    public ResponseEntity<Response> findBook(@PathVariable String title){
       List<Book> book = bookService.getByTitle(title);
        Response<List> responseBook = new Response<>(HttpStatus.OK, book);

        return new ResponseEntity<>(responseBook,HttpStatus.OK);
    }

    //--------------------------------- Delete Book By (id) -----------------------------------
    @PutMapping("/delete/isbn={isbn}")
    public ResponseEntity<Response> delete(@PathVariable String isbn){
        Book deletedBook = bookService.deleteBook(isbn);
        Response<String> bookResponse = new Response<>(HttpStatus.OK,"The book has been deleted");

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @PutMapping("/update/isbn={isbn}")
    public ResponseEntity<Response> updateBook(@PathVariable String isbn, @RequestBody Book book){
        Book updatedBook = bookService.updateBook(isbn,book);
        Response<Book> bookResponse = new Response<>(HttpStatus.OK,updatedBook);

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }
}
