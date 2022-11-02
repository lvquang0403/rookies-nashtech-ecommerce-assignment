package com.example.ecommerce.dto.request.exception;

import com.example.ecommerce.dto.response.ResponseObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseObject> handleNotFoundException(NotFoundException ex){
        ResponseObject response = new ResponseObject("404",ex.getMessage(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ResponseObject> handleDuplicateException(DuplicateException ex){
        ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), null);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StillRelationException.class)
    public ResponseEntity<ResponseObject> handleStillRelationException(StillRelationException ex){
        ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), null);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseObject> handleBadRequestException(BadRequestException ex){
        ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), null);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                       HttpStatus status, WebRequest request)
    {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(
                "Fail",
                "Argument Not Valid !!",
                errorMap
        ));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
    }
}
