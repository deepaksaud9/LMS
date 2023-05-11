package com.LMS.service;

import com.LMS.model.Author;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {
    public Author saveAuthor(Author author);
    public List<Author> getAllAuthor();
    public List<Author> findAuthorByName(String Author);
    public Author getAuthorById(long id);
    public Author findAuthorByEmail(String Email);
    public Author deleteAuthor(long id);
}
