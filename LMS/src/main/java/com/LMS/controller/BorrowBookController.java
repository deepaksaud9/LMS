package com.LMS.controller;

import com.LMS.dto.BookResponseDto;
import com.LMS.dto.BorrowBookRequestDto;
import com.LMS.dto.BorrowBookResponseDto;
import com.LMS.dto.BorrowDto;
import com.LMS.model.BorrowBook;
import com.LMS.repository.BorrowBookRepository;
import com.LMS.response.Response;
import com.LMS.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowBookController {

    @Autowired
    private BorrowBookService borrowBookService;
    @PostMapping("/add")
    public ResponseEntity<Response> addBorrow(@RequestBody BorrowBookRequestDto borrowBookRequestDto ){
        BorrowBookResponseDto borrow = borrowBookService.addBorrowBook(borrowBookRequestDto);
        Response<BorrowBookResponseDto> borrowDtoResponse = new Response<>(HttpStatus.CREATED, borrow);

    return new ResponseEntity<Response>(borrowDtoResponse, HttpStatus.CREATED);
    }

}
