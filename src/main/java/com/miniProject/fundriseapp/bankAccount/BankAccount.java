package com.miniProject.fundriseapp.bankAccount;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.miniProject.fundriseapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class BankAccount {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank(message = "balance shouldnot be blank")
    private double balance;
    @NotBlank(message = "Accountname shouldnot be blank")
    private String AccountName;
    @NotBlank(message = "Accountnumber shouldnot be blank")
    private Integer AccountNumber;

    @NotBlank(message = "cvv shouldnot be blank")
    private Integer cvv;
    @NotBlank(message = "BankName shouldnot be blank")
    private String BankName;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BankAccount() {
    }

    public BankAccount(Integer id, double balance, String accountName, Integer accountNumber, Integer cvv, String bankName, User user) {
        this.id = id;
        this.balance = balance;
        AccountName = accountName;
        AccountNumber = accountNumber;
        this.cvv = cvv;
        BankName = bankName;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "id=" + id +
                ", balance=" + balance +
                ", AccountName='" + AccountName + '\'' +
                ", AccountNumber=" + AccountNumber +
                ", cvv=" + cvv +
                ", BankName=" + BankName +
                ", user=" + user +
                '}';
    }
}

