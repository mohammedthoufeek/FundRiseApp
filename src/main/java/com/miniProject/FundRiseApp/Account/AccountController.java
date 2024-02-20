package com.miniProject.FundRiseApp.Account;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("account")
    public Account createAccount(@RequestBody Account account) throws AccountException{
        return this.accountService.createAccount(account);
    }

    @PutMapping("account")
    public Boolean updateAccount(@RequestBody Account account){
        return this.accountService.updateAccountNameById(account.getId());
    }

    @GetMapping("account/{id}")
    public Account getAccountById(@PathVariable("id") Integer accountId){
        return this.accountService.getAccountById(accountId);
    }

    @PatchMapping("account/{id}")
    public Double depositFundsById(@RequestBody Integer accountId){
        return this.accountService.depositFundsById(accountId);
    }

    @PutMapping("account")
    public Double withdrawAllFunds(){
        return this.accountService.withdrawAllFunds();
    }

    @DeleteMapping("account/{id}")
    public Account deleteAccountById(@PathVariable("id") Integer accountId){
        return this.accountService.deleteAccountById(accountId);
    }
}
