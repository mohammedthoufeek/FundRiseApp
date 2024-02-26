package com.miniProject.FundRiseApp.Payments;

import com.miniProject.FundRiseApp.Account.Account;
import com.miniProject.FundRiseApp.Account.AccountDto;
import com.miniProject.FundRiseApp.Account.AccountRepo;
import com.miniProject.FundRiseApp.Post.Post;
import com.miniProject.FundRiseApp.Post.PostRepo;
import com.miniProject.FundRiseApp.User.User;
import com.miniProject.FundRiseApp.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    @Override
    public Payments addPayments(PaymentDto paymentDto) throws PaymentsException {
        User user = this.userRepo.findById(paymentDto.getUserId()).get();
        Account account = user.getAccountDetails();
        Post postObj = this.postRepo.findById(paymentDto.getPostId()).get();
        Payments paymentsObj1 = new Payments(paymentDto.getAmount(), paymentDto.getDate(), paymentDto.getTime(), user, postObj );
        account.setBalance(account.getBalance()+paymentDto.getAmount());
        Payments paymentsObj2 = this.paymentRepo.save(paymentsObj1);
        postObj.getPayments().add(paymentsObj2);
        this.postRepo.save(postObj);
        this.accountRepo.save(account);
        return paymentsObj2;
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
