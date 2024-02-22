package com.miniProject.FundRiseApp.Payments;

import com.miniProject.FundRiseApp.Account.Account;

public class PaymentDto {

    private Account account;
    Payments payment;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Payments getPayment() {
        return payment;
    }

    public void setPayment(Payments payment) {
        this.payment = payment;
    }
}
