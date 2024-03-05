package com.miniProject.fundriseapp.payments;

import com.miniProject.fundriseapp.payments.PaymentsException;
import com.miniProject.fundriseapp.user.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentControllerAdvice {
    @ExceptionHandler(value = {PaymentsException.class})
    public ResponseEntity<String> handleUserException(PaymentsException e){
        System.out.println("working");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


