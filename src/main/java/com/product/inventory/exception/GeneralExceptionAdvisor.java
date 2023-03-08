package com.product.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handle(NotFoundException exception) {
        return handleBadRequest(exception.getMessage());
    }

    @ExceptionHandler(EnoughMaterialNotFoundException.class)
    public ResponseEntity<Object> handle(EnoughMaterialNotFoundException exception) {
        return handleBadRequest(exception.getMessage());
    }

    @ExceptionHandler(ExistException.class)
    public ResponseEntity<Object> handle(ExistException exception) {
        return handleBadRequest(exception.getMessage());
    }

    private ResponseEntity<Object> handleBadRequest(String s) {
        return new ResponseEntity<>(s, HttpStatus.BAD_REQUEST);
    }
}
