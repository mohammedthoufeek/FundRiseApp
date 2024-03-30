package com.miniProject.fundriseapp;

import com.miniProject.fundriseapp.bankAccount.*;
import com.miniProject.fundriseapp.user.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankAccountServiceTest {

    @Autowired
    private BankAccountService bankAccountService;


    public BankAccountServiceTest() {
    }

    @Test
    @Order(1)
    void createAccountTest() throws BankAccountException {
        try {
            assertNotNull(bankAccountService.createAccount(new BankAccount(2, 200.0, "dhanush", 234, 111, "SBI"), 1));
        } catch (BankAccountException e) {
            throw new BankAccountException(e.getMessage());
        }
    }

    @Test
    @Order(2)
    void duplicateAccountCreation() {
        assertThrows(BankAccountException.class, () -> {
            this.bankAccountService.createAccount(new BankAccount(2, 200.0, "dhanush", 234, 111, "SBI"), 1);
        });
    }


    @Test
    @Order(3)
    void testGetAccountById_Positive () {
        BankAccountServiceImpl bankAccountService = new BankAccountServiceImpl();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1);

        User user = new User();
        user.setId(1);
        user.setAccountDetails(bankAccount);
        BankAccount retrievedAccount = null;
        try {
            retrievedAccount = bankAccountService.getAccountById(1);
        } catch (BankAccountException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(retrievedAccount);
        assertEquals(1, retrievedAccount.getId());
    }

    @Test
    @Order(4)
    public void testGetAccountById_Negative_UserNotFound () {
        BankAccountServiceImpl bankAccountService = new BankAccountServiceImpl();
        BankAccountException exception = assertThrows(BankAccountException.class,
                () -> bankAccountService.getAccountById(1));
        assertEquals("User not found to add account", exception.getMessage());
    }

    @Test
    @Order(5)
    public void testUpdateAccountNameById_Positive () {
        BankAccountServiceImpl bankAccountService = new BankAccountServiceImpl();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1);
        boolean isUpdated = false;
        try {
            isUpdated = bankAccountService.updateAccountNameById(1, "New Account Name");
        } catch (BankAccountException e) {
            throw new RuntimeException(e);
        }
        assertTrue(isUpdated);
        assertEquals("New Account Name", bankAccount.getAccountName());
    }

    @Test
    @Order(6)
    public void testUpdateAccountNameById_Negative_AccountNotFound () {
        BankAccountServiceImpl bankAccountService = new BankAccountServiceImpl();
        BankAccountException exception = assertThrows(BankAccountException.class,
                () -> bankAccountService.updateAccountNameById(1, "New Account Name"));

        assertEquals("Account id does not exist: 1", exception.getMessage());
    }

    @Test
    @Order(7)
    void validAccountNumber() {
        try {
            Assertions.assertNotEquals(bankAccountService.getAccountById(2).getAccountNumber(), 1234);
        } catch (BankAccountException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    @Order(8)
    void validCvvNumber() throws BankAccountException {
        try {
            Assertions.assertNotEquals(bankAccountService.getAccountById(2).getCvv(), 110);
        } catch (BankAccountException e) {
            throw new BankAccountException(e.getMessage());
        }
    }

}


