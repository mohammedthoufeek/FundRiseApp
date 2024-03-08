package com.miniProject.fundriseapp.transactions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionControllerAdvice {
    @ExceptionHandler(value = {TransactionException.class})
    public ResponseEntity<String> handleUserException(TransactionException e){
        System.out.println("working");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


