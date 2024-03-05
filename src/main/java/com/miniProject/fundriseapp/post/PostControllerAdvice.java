package com.miniProject.fundriseapp.post;

import com.miniProject.fundriseapp.user.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class PostControllerAdvice {
    @ExceptionHandler(value = {PostException.class})
    public ResponseEntity<String> handlePostException(PostException e){
        System.out.println("working");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
