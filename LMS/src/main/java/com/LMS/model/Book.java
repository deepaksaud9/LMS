package com.LMS.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String title;
    @Id
    private String isbn;
    @ManyToOne
    private Author author;
    private String publication;
    private long quantity=1;
    private boolean status=true;

    public Book(String isbn){
        this.isbn = isbn;
    }

    public Book(String isbn, String title, long author, String publication,long quantity){
        this.title=title;
        this.isbn=isbn;
        this.publication=publication;
        this.quantity=quantity;
        this.author=new Author(author);
    }




}
