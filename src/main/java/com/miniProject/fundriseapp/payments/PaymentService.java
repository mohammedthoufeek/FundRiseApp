package com.miniProject.fundriseapp.payments;

import java.util.List;

public interface PaymentService {

    Payments addPayments(PaymentDto paymentDto) throws PaymentsException;



    List<Payments> getAllPayments();

    Payments getPaymentById(Integer id);
}
