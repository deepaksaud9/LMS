package com.LMS.repository;

import com.LMS.model.Author;
import com.LMS.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Optional<Author> findByNameAndStatus(String authorName, boolean status);

    public Optional<Author> findByEmail(String email);

    public List<Author> findAllByStatus(boolean status);
    public Optional<Author> findByAuthorIdAndStatus(long authorId,boolean status);


    //new declaration
    Optional<Author> findByName(String name);

}
