package com.LMS.serviceImpl;

import com.LMS.dto.BookResponseDto;
import com.LMS.exception.AlreadyExistException;
import com.LMS.exception.NotFoundException;
import com.LMS.model.Book;
import com.LMS.repository.AuthorRepository;
import com.LMS.repository.BookRepository;
import com.LMS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public BookResponseDto addBook(BookResponseDto bookDTO) {
        Optional<Book> isIsbn = bookRepo.findByIsbnAndStatus(bookDTO.getIsbn(),true);
        if(isIsbn.isPresent()){
            throw new AlreadyExistException("this book already exists");
        }
        Book book = bookRepo.save(this.dtoToBook(bookDTO));
        return bookToDto(book);
    }

    //--------------------------------------Find All Books By status true -------------------------
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepo.findAllByStatus(true);
        if(books==null){
            throw new NotFoundException("No books are present");
        }

        return bookRepo.findAllByStatus(true);
    }
//---------------------get Book By Id--------------------
    @Override
    public Book getBookByIsbn(String isbn) {
        Book book = bookRepo.findByIsbn(isbn).get();
        if(book.isStatus()==false){
            throw new NotFoundException("This isn't available");
        }

        return bookRepo.findByIsbn(isbn).get();
    }


    //----------------------------- Find Book By Book_Name and Status=true  ----------------------------
    @Override
    public List<Book> getByTitle(String title) {
       List<Book> books = bookRepo.findByTitleAndStatus(title, true);
       if(books == null){
           throw new NotFoundException("No books are available");
       }
        return books;
    }

    //----------------------------- Update Books -----------------------------
    @Override
    public Book updateBook(String isbn, Book book) {
        Book bookData = bookRepo.findByIsbn(isbn).get();
        if (bookData == null) {
            throw new NotFoundException("WTF!!! This book doesn't exist bro.");
        }
        if (bookData.isStatus() == false) {
            throw new NotFoundException("this book is not available");
        }
        if (bookData.getIsbn().equals(book.getIsbn())) {
            throw new NotFoundException("this ISBN can be changed");
        }
        Book updatedBook = bookData;
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setIsbn(book.getTitle());
        updatedBook.setPublication(book.getPublication());
        updatedBook.setQuantity(book.getQuantity());
        return bookRepo.save(updatedBook);
    }

    //---------------------- delete Book ----------------
    @Override
    public Book deleteBook(String isbn) {
        Optional<Book> delete = bookRepo.findByIsbnAndStatus( isbn,true);
        if (!delete.isPresent()){
           throw new NotFoundException("the book is already deleted");
        }
        Book deletedBook=delete.get();
        deletedBook.setStatus(false);
        bookRepo.save(deletedBook);
        return deletedBook;
    }

    private  Book dtoToBook(BookResponseDto bookDTO){
        Book newBook = new Book(bookDTO.getIsbn(),bookDTO.getTitle(),bookDTO.getAuthorId(),bookDTO.getPublication(),bookDTO.getQuantity());
        return newBook;
    }

    private BookResponseDto bookToDto(Book book){
        BookResponseDto bookDTO=new BookResponseDto();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setAuthorId(book.getAuthor().getAuthorId());
        bookDTO.setPublication(book.getPublication());
        bookDTO.setQuantity(book.getQuantity());
        return bookDTO;
    }
}
