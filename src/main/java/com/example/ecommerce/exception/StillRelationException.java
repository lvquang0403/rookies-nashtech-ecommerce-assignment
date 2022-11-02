package com.example.ecommerce.dto.request.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StillRelationException extends RuntimeException {
    public StillRelationException(String message){
        super(message);
    }

}
