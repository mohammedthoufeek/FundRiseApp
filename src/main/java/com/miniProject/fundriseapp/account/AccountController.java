package com.miniProject.fundriseapp.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("account")
    public Account createAccount(@RequestBody Account account, Integer id) throws AccountException{
        return this.accountService.createAccount(account,id);
    }

    @PatchMapping("account")
    public Boolean updateAccount(@PathVariable Integer id, String name) throws AccountException {
        return this.accountService.updateAccountNameById(id,name);
    }

    @GetMapping("account/{id}")
    public Account getAccountById(@PathVariable("id") Integer accountId) throws AccountException{
        return this.accountService.getAccountById(accountId);
    }




    @DeleteMapping("account/{id}")
    public Account deleteAccountById(@PathVariable("id") Integer accountId) throws AccountException{
        return this.accountService.deleteAccountById(accountId);
    }
}

