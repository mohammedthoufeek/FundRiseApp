package com.miniProject.FundRiseApp.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account newAccount) {
        return null;
    }

    @Override
    public Account getAccountById(Integer accountId) {
        return null;
    }

    @Override
    public Double depositFundsById(Integer accountId) {
        return null;
    }

    @Override
    public Double withdrawAllFunds() {
        return null;
    }

    @Override
    public Boolean updateAccountNameById(Integer accountId) {
        return null;
    }

    @Override
    public Account deleteAccountById(Integer accountId) {
        return null;
    }

}
