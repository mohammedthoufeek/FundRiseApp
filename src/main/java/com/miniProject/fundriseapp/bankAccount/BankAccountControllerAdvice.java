package com.miniProject.fundriseapp.bankAccount;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BankAccountControllerAdvice {
    @ExceptionHandler(value = {BankAccountException.class})
    public ResponseEntity<String> handlePostException(BankAccountException e){
        System.out.println("working");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


