package com.LMS.repository;

import com.LMS.model.Author;
import com.LMS.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book , String> {
    public Optional<Book> findByIsbnAndStatus(String isbn,boolean status);
    public List<Book> findByTitleAndStatus(String title, boolean status);

    public List<Book> findAllByStatus(boolean status);
    public List<Book> findByStatus(boolean status);

    // new declaration
    Optional<Book> findByTitle(String title);
    Optional<Book> findByIsbn(String isbn);
}
