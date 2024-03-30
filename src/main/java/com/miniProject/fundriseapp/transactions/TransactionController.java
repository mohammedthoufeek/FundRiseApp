package com.miniProject.fundriseapp.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("payment")
    public Transaction addPayment(@RequestBody TransactionDto transactionDto) throws TransactionException {
        System.out.println(transactionDto);
        return this.transactionService.addPayments(transactionDto);
    }

    @GetMapping("payment")
    public List<Transaction> getAllPayments()throws TransactionException {
        return this.transactionService.getAllPayments();
    }

    @GetMapping("payment/{id}")
    public Transaction getPaymentById(@PathVariable Integer id)throws TransactionException {
        return this.transactionService.getPaymentById(id);
    }

}


