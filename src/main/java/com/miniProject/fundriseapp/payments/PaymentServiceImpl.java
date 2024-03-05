package com.miniProject.fundriseapp.payments;

import com.miniProject.fundriseapp.account.Account;
import com.miniProject.fundriseapp.account.AccountRepo;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostRepo;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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
        if(user==null) throw new PaymentsException("User not found");
        Account account = user.getAccountDetails();
        if(account==null) throw new PaymentsException("Account not present");
        Post postObj = this.postRepo.findById(paymentDto.getPostId()).get();
        if(user.getId()== postObj.getUser().getId()) throw new PaymentsException("Post user can't able to make payment");
        if(postObj==null) throw new PaymentsException("Unavailable Post");
        if(paymentDto.getAmount()<0) throw new PaymentsException("Check your amount entered");
        Payments paymentsObj1 = new Payments(paymentDto.getAmount(), paymentDto.getDate(), paymentDto.getTime(), user, postObj );
        if(paymentsObj1==null) throw new PaymentsException("Payment user1 not present");
        account.setBalance(account.getBalance()+paymentDto.getAmount());
        paymentsObj1.setTime(LocalTime.now());
        Payments paymentsObj2 = this.paymentRepo.save(paymentsObj1);
        if(paymentsObj2==null) throw new PaymentsException("Payment user2 not present");

        postObj.getPayments().add(paymentsObj2);
        postObj.setAmountReceived(postObj.getAmountReceived()+paymentDto.getAmount());
        this.postRepo.save(postObj);
        this.accountRepo.save(account);
        return paymentsObj2;
    }

    @Override
    public List<Payments> getAllPayments() throws PaymentsException {
        if(paymentRepo==null) throw new PaymentsException("Payments not found");
        return this.paymentRepo.findAll();
    }

    @Override
    public Payments getPaymentById(Integer id) throws PaymentsException {
        Optional<Payments> paymentsOpt = this.paymentRepo.findById(id);
        if(paymentsOpt.isEmpty()) throw new PaymentsException("Payment is not found");
        Payments payments = paymentsOpt.get();
        return payments;
    }


}
