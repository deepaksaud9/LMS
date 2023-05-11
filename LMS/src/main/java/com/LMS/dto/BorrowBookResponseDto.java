package com.LMS.dto;

import com.LMS.model.Book;
import com.LMS.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowBookResponseDto {

    private long userId;
    private String isbn;
    private LocalDate requestDate;

}
