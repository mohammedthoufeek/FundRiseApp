package com.miniProject.FundRiseApp.Payments;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface PaymentService {
    Payments addPayments(Integer accountId,Payments newPayment) throws PaymentsException;
    public List<Payments> getAllPayments();
    public Payments getPaymentById(Integer id);

}
