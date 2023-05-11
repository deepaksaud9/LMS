package com.LMS.repository;

import com.LMS.model.User;
import com.LMS.model.UserEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByStatus(UserEnum active);
    public Optional<User> findByIdAndStatus(long id ,UserEnum userEnum);
//    public boolean existsByIdAndStatus(long id ,UserEnum userEnum);

    //new declaration
    public Optional<User> findByEmail(String email);

    public List<User> findAllByStatus(UserEnum closed);
}
