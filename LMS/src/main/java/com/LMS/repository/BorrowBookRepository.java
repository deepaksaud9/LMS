package com.LMS.repository;

import com.LMS.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    List<BorrowBook> findByUserId(Long userId);
    List<BorrowBook> findByBookIsbn(String isbn);
}
