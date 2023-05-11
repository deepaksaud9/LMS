package com.LMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long borrowId;
    private LocalDate borrowDate = LocalDate.now();
    private LocalDate returnedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
   private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_isbn")
   private Book book;

    private Integer fine=0;

}
