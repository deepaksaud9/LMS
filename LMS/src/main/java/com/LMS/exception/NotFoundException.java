package com.LMS.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class NotFoundException extends RuntimeException{
    private int errorCode;
    private String status;
    private String message;

    public NotFoundException(String message){
        this.errorCode = HttpStatus.BAD_REQUEST.value();
        this.status = HttpStatus.BAD_REQUEST.name();
        this.message = message;
    }

}
