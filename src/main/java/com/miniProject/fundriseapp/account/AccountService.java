package com.miniProject.fundriseapp.account;

public interface AccountService {


    //create account
    Account createAccount(Account newAccount, Integer userId) throws AccountException;

    //read account
    Account getAccountById(Integer accountId);
    //update account


    //Double withdrawAllFunds();
    Boolean updateAccountNameById(Integer accountId, String name) throws AccountException;
    //delete
    Account deleteAccountById(Integer accountId);

}

