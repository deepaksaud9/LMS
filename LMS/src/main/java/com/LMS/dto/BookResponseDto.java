package com.LMS.dto;


import lombok.Data;

@Data
public class BookResponseDto {
    private String isbn;
    private String title;
    private long authorId;
    private String publication;
    private long quantity;

}
