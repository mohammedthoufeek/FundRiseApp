package com.miniProject.FundRiseApp.Payments;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface PaymentService {
    public List<Payments> getAllPayments();
    public Payments getPaymentById(Integer id);

    Payments addPayments(PaymentDto paymentDto) throws PaymentsException;
}
