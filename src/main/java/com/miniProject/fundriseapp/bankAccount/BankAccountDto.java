package com.miniProject.fundriseapp.bankAccount;

public class BankAccountDto {

    private Integer userId;
    BankAccount bankAccount;

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BankAccount getAccount() {
        return bankAccount;
    }

    public void setAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}