package com.LMS.serviceImpl;

import com.LMS.exception.AlreadyExistException;
import com.LMS.exception.NotFoundException;
import com.LMS.model.Author;
import com.LMS.repository.AuthorRepository;
import com.LMS.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public Author saveAuthor(Author author) {
        Optional<Author> optionalAuthor = authorRepo.findByEmail(author.getEmail());
        System.out.println(optionalAuthor);
        if(optionalAuthor.isPresent())
        {
            throw new AlreadyExistException("Author already exist");
        }
//        authorRepo.save(author);
        return authorRepo.save(author);
    }

    @Override
    public List<Author> getAllAuthor() {
        List<Author> authors = authorRepo.findAll();

        if(authors.isEmpty()){
            throw new NotFoundException("No Authors are present at this time");
        }

        return authorRepo.findAllByStatus(true);
    }

    @Override
    public List<Author> findAuthorByName(String Author) {
        return null;
    }


    @Override
    public Author getAuthorById(long id) {
        Optional<Author> author = authorRepo.findById(id);
        if(author.get().isStatus()==false){
            throw new NotFoundException("Not found");
        }
        return authorRepo.findById(id).get();

    }

    @Override
    public Author findAuthorByEmail(String email) {
        Optional<Author> author = authorRepo.findByEmail(email);

        if(author.get()==null){
            throw new NotFoundException("Author Not found");
        }
        return authorRepo.findByEmail(email).get();
    }

    @Override
    public Author deleteAuthor(long id) {
        Optional<Author> author = authorRepo.findById(id);
        return null;
    }
}
