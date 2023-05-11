package com.LMS.controller;

import com.LMS.internationalization.LocaleResolverConfig;
import com.LMS.model.Author;
import com.LMS.response.Response;
import com.LMS.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private LocaleResolverConfig messageSource=new LocaleResolverConfig();

    @PostMapping("/author")
    public ResponseEntity<Response> createAuthor(@RequestBody Author author){
       Author addAuthor = authorService.saveAuthor(author);
       Response<Author> respondAuthor = new Response<>(HttpStatus.CREATED, addAuthor);

        return new ResponseEntity<Response>(respondAuthor,HttpStatus.CREATED);
    }

    @GetMapping("/author")
    public ResponseEntity<Response> getAllAuthor(){
            List<Author> authors = authorService.getAllAuthor();
            Response<List> authorResponse = new Response<>(HttpStatus.OK, authors);

        return new ResponseEntity<Response>(authorResponse,HttpStatus.OK);
    }

//    @GetMapping("/i18n")
//    public ResponseEntity<?> geti18n(@RequestHeader("Accept-Language") String language){
//        String str = messageSource.getMessageSource().getMessage("message.hello",null,new Locale(language));
//        Response<String> response=new Response<>(HttpStatus.OK,str);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }

    @GetMapping("/author/id={id}")
    public ResponseEntity<Response> getAuthor(@PathVariable Long id){
        Author author = authorService.getAuthorById(id);
        Response<Author> authorResponse = new Response<>(HttpStatus.OK, author);

        return new ResponseEntity<Response>(authorResponse, HttpStatus.OK);
    }

    @GetMapping("/author/email={email}")
    public ResponseEntity<Response> getAuthorByEmail(@PathVariable String email){
        Author author = authorService.findAuthorByEmail(email);
        Response<Author> authorResponse = new Response<>(HttpStatus.OK, author);

        return new ResponseEntity<Response>(authorResponse, HttpStatus.OK);
    }
}
