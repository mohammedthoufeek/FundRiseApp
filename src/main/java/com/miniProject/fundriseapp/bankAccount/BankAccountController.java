package com.miniProject.fundriseapp.bankAccount;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})

public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;



    @PostMapping("account/{id}")
    public BankAccount createAccount(@Valid @RequestBody BankAccount bankAccount,@PathVariable Integer id) throws BankAccountException {

        return this.bankAccountService.createAccount(bankAccount,id);

    }

    @PatchMapping("account")
    public Boolean updateAccount(@PathVariable Integer id, String name) throws BankAccountException {
        return this.bankAccountService.updateAccountNameById(id,name);
    }

    @GetMapping("account/{id}")
    public BankAccount getAccountById(@PathVariable Integer id) throws BankAccountException {
        return this.bankAccountService.getAccountById(id);
    }

    @DeleteMapping("account/{id}")
    public BankAccount deleteAccountById(@PathVariable("id") Integer accountId) throws BankAccountException {
        return this.bankAccountService.deleteAccountById(accountId);
    }
}

