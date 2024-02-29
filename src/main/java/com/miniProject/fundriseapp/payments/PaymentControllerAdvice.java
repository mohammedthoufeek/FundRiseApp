package com.miniProject.fundriseapp.payments;

import com.miniProject.fundriseapp.payments.PaymentsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class PaymentControllerAdvice {
    @ExceptionHandler(value = {PaymentsException.class})
    public ResponseEntity<String> handlePostException(PaymentsException e){
        System.out.println("working");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


