package com.miniProject.fundriseapp.bankAccount;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("account")
    public BankAccount createAccount(@Valid @RequestBody BankAccount bankAccount, Integer id) throws BankAccountException {
        return this.bankAccountService.createAccount(bankAccount,id);
    }

    @PatchMapping("account")
    public Boolean updateAccount(@PathVariable Integer id, String name) throws BankAccountException {
        return this.bankAccountService.updateAccountNameById(id,name);
    }

    @GetMapping("account/{id}")
    public BankAccount getAccountById(@PathVariable("id") Integer accountId) throws BankAccountException {
        return this.bankAccountService.getAccountById(accountId);
    }




    @DeleteMapping("account/{id}")
    public BankAccount deleteAccountById(@PathVariable("id") Integer accountId) throws BankAccountException {
        return this.bankAccountService.deleteAccountById(accountId);
    }
}

