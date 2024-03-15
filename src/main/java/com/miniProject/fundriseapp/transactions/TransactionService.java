package com.miniProject.fundriseapp.transactions;

import java.util.List;

public interface TransactionService {
    Transaction addPayments(TransactionDto transactionDto) throws TransactionException;

    List<Transaction> getAllPayments() throws TransactionException;

    Transaction getPaymentById(Integer id) throws TransactionException;
}
