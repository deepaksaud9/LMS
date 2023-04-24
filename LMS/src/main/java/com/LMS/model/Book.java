package com.LMS.model;

import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int bookId;
    private String title;
    private String author;
    private String price;

}
