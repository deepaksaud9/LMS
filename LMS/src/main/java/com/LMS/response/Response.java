package com.LMS.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private int statusCode;
    private String status;
    private T response;  //    --------------message(object)-----------


    public Response(HttpStatus httpStatus,T t) {
        this.statusCode = httpStatus.value();
        this.status = httpStatus.name();
        this.response = t;
    }



}
