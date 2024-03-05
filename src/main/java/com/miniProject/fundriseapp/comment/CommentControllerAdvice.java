package com.miniProject.fundriseapp.comment;

import com.miniProject.fundriseapp.account.AccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentControllerAdvice {
    @ExceptionHandler(value = {CommentException.class})
    public ResponseEntity<String> handlePostException(CommentException e){
        System.out.println("working");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
