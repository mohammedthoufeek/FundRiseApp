package com.miniProject.fundriseapp.account;

public interface AccountService {

    //create account
    Account createAccount(Account newAccount, Integer userId) throws AccountException;

    //read account
    Account getAccountById(Integer accountId);
    //update account
    Double depositFundsById(Integer accountId, Double amount) throws AccountException;

    //Double withdrawAllFunds();
    Account updateAccountNameById(Integer accountId, Account account) throws AccountException;
    //delete
    Account deleteAccountById(Integer accountId);

}