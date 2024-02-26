package com.miniProject.FundRiseApp.Payments;

import com.miniProject.FundRiseApp.Account.AccountDto;
import com.miniProject.FundRiseApp.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("payment")
    public Payments addPayment(@RequestBody PaymentDto paymentDto) throws PaymentsException {
        return this.paymentService.addPayments(paymentDto);
    }

    @GetMapping("payment")
    public List<Payments> getAllPayments(){
        return this.paymentService.getAllPayments();
    }

    @GetMapping("payment/{id}")
    public Payments getPaymentById(@PathVariable Integer id){
        return this.paymentService.getPaymentById(id);
    }



}
