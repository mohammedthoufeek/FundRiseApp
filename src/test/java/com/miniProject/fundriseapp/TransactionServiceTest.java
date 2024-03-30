package com.miniProject.fundriseapp;

import com.miniProject.fundriseapp.bankAccount.BankAccount;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.transactions.*;
import com.miniProject.fundriseapp.user.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTest {


    @Test
    @Order(1)
    public void testAddPayments_Positive() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();

        User user = new User();
        user.setId(1);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000);
        user.setAccountDetails(bankAccount);

        Post post = new Post();
        post.setId(1);
        post.setUser(new User()); // Assuming post user is different from the current user

        TransactionDto transactionDto = new TransactionDto(1, 1, 500.0); // User 1 paying 500 for Post 1

        Transaction addedTransaction = null;
        try {
            addedTransaction = transactionService.addPayments(transactionDto);
        } catch (TransactionException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(addedTransaction);
        assertEquals(500, bankAccount.getBalance());
        assertEquals(500, post.getAmountReceived());
    }

    @Test
    @Order(2)
    public void testAddPayments_Negative_InsufficientBalance() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();

        User user = new User();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(100);

        TransactionDto transactionDto = new TransactionDto(1, 1, 200.0); // User 1 trying to pay more than balance

        TransactionException exception = assertThrows(TransactionException.class,
                () -> transactionService.addPayments(transactionDto));

        assertEquals("Insufficient balance", exception.getMessage());
    }


    @Test
    @Order(3)
    public void testGetAllPayments_Positive() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1, LocalDate.now(), LocalTime.now(), new User(), new Post()));
        transactionList.add(new Transaction(2, LocalDate.now(), LocalTime.now(), new User(), new Post()));

        List<Transaction> allPayments = null;
        try {
            allPayments = transactionService.getAllPayments();
        } catch (TransactionException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(allPayments);
        assertEquals(2, allPayments.size());
    }

    @Test
    @Order(4)
    public void testGetAllPayments_Negative_NotFound() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();

        TransactionException exception = assertThrows(TransactionException.class,
                () -> transactionService.getAllPayments());

        assertEquals("Payments not found", exception.getMessage());
    }

    @Test
    @Order(5)
    public void testGetPaymentById_Positive() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();

        Transaction transaction = new Transaction(1,LocalDate.now(), LocalTime.now(), new User(), new Post());
        Transaction retrievedPayment = null;
        try {
            retrievedPayment = transactionService.getPaymentById(1);
        } catch (TransactionException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(retrievedPayment);
        assertEquals(1, retrievedPayment.getId());
    }

    @Test
    @Order(6)
    public void testGetPaymentById_Negative_NotFound() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();

        TransactionException exception = assertThrows(TransactionException.class,
                () -> transactionService.getPaymentById(1));

        assertEquals("Payment is not found", exception.getMessage());
    }
    @Test
    @Order(7)
    public void testGetPaymentById_Negative_NullTransaction() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();
        TransactionException exception = assertThrows(TransactionException.class,
                () -> transactionService.getPaymentById(1));

        assertEquals("Payment is not found", exception.getMessage());
    }

}
