package com.LMS.handler;

import com.LMS.exception.AlreadyExistException;
import com.LMS.exception.NotFoundException;
import com.LMS.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<Response> alreadyExistExceptionHandler(AlreadyExistException alreadyExistException){
        Response response = new Response<String>(HttpStatus.BAD_REQUEST,alreadyExistException.getMessage());
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> notFoundExceptionHandler(NotFoundException notFoundException){
        Response response = new Response<String>(HttpStatus.BAD_REQUEST,notFoundException.getMessage());
        return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity<Response> illegalStateExceptionHandler

}
