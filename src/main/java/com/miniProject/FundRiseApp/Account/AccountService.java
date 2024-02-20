package com.miniProject.FundRiseApp.Account;

public interface AccountService {

    //create account
    Account createAccount(Account newAccount);
    //read account
    Account getAccountById(Integer accountId);
    //update account
    Double depositFundsById(Integer accountId);

    Double withdrawAllFunds();
    Boolean updateAccountNameById(Integer accountId);
    //delete
    Account deleteAccountById(Integer accountId);

}
