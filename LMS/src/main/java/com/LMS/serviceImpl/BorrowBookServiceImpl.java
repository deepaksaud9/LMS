package com.LMS.serviceImpl;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.LMS.dto.BorrowBookRequestDto;
import com.LMS.dto.BorrowBookResponseDto;
import com.LMS.dto.BorrowDto;
import com.LMS.exception.NotFoundException;
import com.LMS.model.Book;
import com.LMS.model.BorrowBook;
import com.LMS.model.User;
import com.LMS.repository.BookRepository;
import com.LMS.repository.BorrowBookRepository;
import com.LMS.repository.UserRepository;
import com.LMS.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {

    @Autowired
    private BorrowBookRepository borrowBookRepo;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepo;
    @Override
    public BorrowBookResponseDto addBorrowBook(BorrowBookRequestDto borrowBookRequestDto) {
        Optional<Book> book = bookRepository.findByIsbn(borrowBookRequestDto.getIsbn());
        System.out.println("isbn  number "+borrowBookRequestDto);
        if (!book.isPresent()){
            throw new NotFoundException("Book is not found");
        }

        BorrowBook toDoBorrowBook = DtoToBorrowBook(borrowBookRequestDto);

        Book todoBook = book.get();
        todoBook.setQuantity(todoBook.getQuantity()-1);

        bookRepository.save(todoBook);

//        borrowBook.setBook(todoBook);
//        borrowBook.setBorrowDate(LocalDate.now());
//        borrowBook.setUser(userRepo.findById(borrowBook.getUser().getId()).get());
//        return borrowBookRepo.save(borrowBook);
       BorrowBook savedBorrow =  borrowBookRepo.save(toDoBorrowBook);
       return borrowBookToDto(savedBorrow);

    }


    public BorrowBook DtoToBorrowBook(BorrowBookRequestDto borrowBookRequestDto){
        BorrowBook borrowBook  = new BorrowBook();
        Book reqBook = bookRepository.findByIsbn(borrowBookRequestDto.getIsbn()).get();
        borrowBook.setBook(reqBook);
        User reqUser = userRepo.findById(borrowBookRequestDto.getUserId()).get();
        borrowBook.setUser(reqUser);
        return borrowBook;
    }

    public BorrowBookResponseDto borrowBookToDto(BorrowBook borrowBook){
        BorrowBookResponseDto borrowBookResponseDto = new BorrowBookResponseDto();
        borrowBookResponseDto.setUserId(borrowBook.getUser().getId());
        borrowBookResponseDto.setIsbn(borrowBook.getBook().getIsbn());
        borrowBookResponseDto.setRequestDate(borrowBook.getBorrowDate());

        return borrowBookResponseDto;
    }
}
