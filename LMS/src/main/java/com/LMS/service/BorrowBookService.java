package com.LMS.service;

import com.LMS.dto.BorrowBookRequestDto;
import com.LMS.dto.BorrowBookResponseDto;
import com.LMS.model.BorrowBook;
import org.springframework.stereotype.Service;
public interface BorrowBookService {
public BorrowBookResponseDto addBorrowBook(BorrowBookRequestDto borrowBookRequestDto);
//    public BorrowBook returnBorrowBook(BorrowBook borrowBook,Long id);

}
