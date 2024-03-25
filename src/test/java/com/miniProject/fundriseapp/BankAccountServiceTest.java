package com.miniProject.fundriseapp;

import com.miniProject.fundriseapp.bankAccount.*;
import com.miniProject.fundriseapp.user.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
public class BankAccountServiceTest {

    @Autowired
    private BankAccountService bankAccountService;


    public BankAccountServiceTest() {
    }

    @DisplayName("Create Account")
    @Test
    @Order(1)
    void createAccountTest() throws BankAccountException {
        try{
            Assertions.assertNotNull(bankAccountService.createAccount(new BankAccount(2,200.0,"dhanush",234,111,"SBI"),1));
        }
        catch (BankAccountException e) {
            throw new BankAccountException(e.getMessage());
        }
    }

    @DisplayName("Duplicate registration")
    @Test
    @Order(2)
    void duplicateAccountCreation(){
        Assertions.assertThrows(BankAccountException.class, () -> {
            this.bankAccountService.createAccount(new BankAccount(2,200.0,"dhanush",234,111,"SBI"),1);
    });
    }


    @DisplayName("Valid Account number")
    @Test
    @Order(3)
    void validAccountNumber(){
        try{
            Assertions.assertNotEquals(bankAccountService.getAccountById(2).getAccountNumber(),1234);
        } catch (BankAccountException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DisplayName("Valid cvv number")
    @Test
    @Order(4)
    void validCvvNumber() throws BankAccountException {
        try{
            Assertions.assertNotEquals(bankAccountService.getAccountById(2).getCvv(),110);
        } catch(BankAccountException e){
            throw new BankAccountException(e.getMessage());
        }
    }

    /*@DisplayName("Account by name exception")
    @Test
    @Order(5)
    void checkByAccountName(){

        Assertions.assertThrows(BankAccountException.class, () ->{
            this.bankAccountService.getAccountById(2).getAccountName();
        });
    }*/

  /*  @DisplayName("Account id null")
    @Test
    @Order(6)*/
    }
