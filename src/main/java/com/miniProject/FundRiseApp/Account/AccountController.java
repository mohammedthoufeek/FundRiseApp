package com.miniProject.FundRiseApp.Account;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("account")
    public Account createAccount(@RequestBody Account account,Integer id) throws AccountException{
        return this.accountService.createAccount(account,id);
    }

    @PatchMapping("account")
    public Boolean updateAccount(@PathVariable Integer id, String name) throws AccountException {
        return this.accountService.updateAccountNameById(id,name);
    }

    @GetMapping("account/{id}")
    public Account getAccountById(@PathVariable("id") Integer accountId){
        return this.accountService.getAccountById(accountId);
    }

    @PatchMapping("account/{id}")
    public Double depositFundsById(@RequestBody Integer accountId, Double amount) throws AccountException {
        return this.accountService.depositFundsById(accountId,amount);
    }

    /*@PutMapping("account")
    public Double withdrawAllFunds(){
        return this.accountService.withdrawAllFunds();
    }*/

    @DeleteMapping("account/{id}")
    public Account deleteAccountById(@PathVariable("id") Integer accountId){
        return this.accountService.deleteAccountById(accountId);
    }
}
