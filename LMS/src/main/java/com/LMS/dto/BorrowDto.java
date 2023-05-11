package com.LMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDto {
    private Long id;
    private Long bookId;
    private Long userId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

}
