package com.miniProject.FundRiseApp.Payments;

import com.miniProject.FundRiseApp.Account.Account;
import com.miniProject.FundRiseApp.Account.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    private AccountRepo accountRepo;

    @Override
    public Payments addPayments(Integer accountId,Payments newPayment) throws PaymentsException {
        Optional<Payments> paymentsOpt=this.paymentRepo.findById(newPayment.getId());
        if(paymentsOpt.isPresent())
            throw new PaymentsException("Comment is already exists");
        Payments paymentsObj= this.paymentRepo.save(newPayment);
        Account accountObj=this.accountRepo.findById(accountId).get();
        //accountObj.getBalance();
        /*this.accountRepo.save(accountObj);
        paymentsObj.se;
        commentRepo.save(commentObj);*/
        return paymentsObj;
    }

    @Override
    public List<Payments> getAllPayments() {
        return this.paymentRepo.findAll();
    }

    @Override
    public Payments getPaymentById(Integer id) {
        Optional<Payments> paymentsOpt = this.paymentRepo.findById(id);
        Payments payments = paymentsOpt.get();
        return payments;
    }
}
