package com.miniProject.fundriseapp;

import com.miniProject.fundriseapp.bankAccount.*;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserException;
import com.miniProject.fundriseapp.user.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
public class AccountServiceTest {
//    @Autowired
//    AccountService accountService;
//    @Test
//    public void createAccountTest(){
//
//        try {
//            Assertions.assertNotNull(accountService.createAccount(new Account(1,200.0,"dhanush",234,545,"SBI"),1));
//        }
//        catch (AccountException e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void createAccountOfNullAccountShouldThrowExceptionTest(){
//        AccountService accountService = new AccountServiceImpl();
//        Assertions.assertThrows(AccountException.class,
//                ()->  accountService.createAccount(null,null ));
//
//    }



}
