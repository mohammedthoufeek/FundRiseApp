package com.miniProject.FundRiseApp.Account;

import com.miniProject.FundRiseApp.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.springframework.web.bind.annotation.PostMapping;

@Entity
public class Account {
    @Id
    // @GeneratedValue
    private Integer id;
    private double balance;
    private String AccountName;
    private Integer AccountNumber;
    private Integer cvv;

    private Integer BankName;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {
    }

    public Account(Integer id, double balance, String accountName, Integer accountNumber, Integer cvv, Integer bankName, User user) {
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

    public Integer getBankName() {
        return BankName;
    }

    public void setBankName(Integer bankName) {
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