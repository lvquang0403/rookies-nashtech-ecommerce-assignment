package com.example.ecommerce.exception;

import com.example.ecommerce.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseObject> handleNotFoundException(NotFoundException ex){
        ResponseObject response = new ResponseObject("404",ex.getMessage(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ResponseObject> handleDuplicateException(DuplicateException ex){
        ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), null);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
