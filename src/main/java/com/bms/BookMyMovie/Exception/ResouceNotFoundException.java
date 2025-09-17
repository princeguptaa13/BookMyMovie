package com.bms.BookMyMovie.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//custom exception class
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResouceNotFoundException extends RuntimeException{

    public ResouceNotFoundException (String message){
        super(message);
    }

}
