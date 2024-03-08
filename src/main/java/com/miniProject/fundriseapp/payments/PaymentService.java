package com.miniProject.fundriseapp.payments;

import java.util.List;

public interface PaymentService {
    Payments addPayments(PaymentDto paymentDto) throws PaymentsException;

    List<Payments> getAllPayments() throws PaymentsException;

    Payments getPaymentById(Integer id) throws PaymentsException;
}
