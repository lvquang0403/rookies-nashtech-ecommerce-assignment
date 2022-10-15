package com.example.ecommerce.exception;

import com.example.ecommerce.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends
        ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseObject> handleProductNotFoundException(String message){
        ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(),message,null);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
