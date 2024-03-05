package com.miniProject.fundriseapp.payments;

import java.util.List;

public interface PaymentService {
    Payments addPayments(PaymentDto paymentDto);

    List<Payments> getAllPayments();

    Payments getPaymentById(Integer id);
}
