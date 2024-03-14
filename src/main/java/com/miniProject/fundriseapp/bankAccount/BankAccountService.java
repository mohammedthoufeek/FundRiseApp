package com.miniProject.fundriseapp.bankAccount;

public interface BankAccountService {


    //create account
    BankAccount createAccount(BankAccount newBankAccount, String email) throws BankAccountException;

    //read account
    BankAccount getAccountById(Integer accountId) throws BankAccountException;
    //update account


    //Double withdrawAllFunds();
    Boolean updateAccountNameById(Integer accountId, String name) throws BankAccountException;
    //delete
    BankAccount deleteAccountById(Integer accountId) throws BankAccountException;

}

