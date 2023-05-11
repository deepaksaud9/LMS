package com.LMS.dto;

import com.LMS.model.Book;
import com.LMS.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowBookRequestDto {


//    private long borrowId;
    private long userId;
    private String isbn;


}
