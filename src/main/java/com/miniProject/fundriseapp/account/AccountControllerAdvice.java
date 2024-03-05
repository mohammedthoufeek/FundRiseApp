package com.miniProject.fundriseapp.account;

import com.miniProject.fundriseapp.account.AccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountControllerAdvice {
    @ExceptionHandler(value = {AccountException.class})
    public ResponseEntity<String> handlePostException(AccountException e){
        System.out.println("working");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


