package com.miniProject.fundriseapp.transactions;

import com.miniProject.fundriseapp.bankAccount.BankAccount;
import com.miniProject.fundriseapp.bankAccount.BankAccountRepo;
import com.miniProject.fundriseapp.notification.Notification;
import com.miniProject.fundriseapp.notification.NotificationRepo;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostRepo;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private BankAccountRepo bankAccountRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private NotificationRepo notificationRepo;

    @Override
    public Transaction addPayments(TransactionDto transactionDto) throws TransactionException {
        User user = this.userRepo.findById(transactionDto.getUserId()).get();
        if(user==null) throw new TransactionException("User not found");
        BankAccount bankAccount = user.getAccountDetails();
        if(bankAccount ==null) throw new TransactionException("Account not present");
        Post postObj = this.postRepo.findById(transactionDto.getPostId()).get();
        if(user.getId()== postObj.getUser().getId()) throw new TransactionException("Post user can't able to make payment");
        if(postObj==null) throw new TransactionException("Unavailable Post");
        if(transactionDto.getAmount()<0) throw new TransactionException("Check your amount entered");
        Transaction transactionObj1 = new Transaction(transactionDto.getAmount(), transactionDto.getDate(), transactionDto.getTime(), user, postObj );
        if(transactionObj1 ==null) throw new TransactionException("Payment user1 not present");
        bankAccount.setBalance(bankAccount.getBalance()+ transactionDto.getAmount());
        transactionObj1.setTime(LocalTime.now());
        Transaction transactionObj2 = this.transactionRepo.save(transactionObj1);
        if(transactionObj2 ==null) throw new TransactionException("Payment user2 not present");

        postObj.getPayments().add(transactionObj2);
        postObj.setAmountReceived(postObj.getAmountReceived()+ transactionDto.getAmount());
        this.postRepo.save(postObj);
        this.bankAccountRepo.save(bankAccount);



        Notification notification=new Notification();
        notification.setPost(postObj);
        Optional<Post> UserId = this.postRepo.findById(postObj.getUser().getId());
        Notification notification1=new Notification(user,postObj,"Amount has been Debited",LocalDate.now(),LocalTime.now());
        this.notificationRepo.save(notification1);
        notification1=new Notification(UserId.get().getUser(),postObj,"Amount has been Credited",LocalDate.now(),LocalTime.now());
        this.notificationRepo.save(notification1);
//        notification.setMessage("Amount has been Credited");
//        notification.setUser(user);
//        notification.setMessage("Amount has been Debited");
        //notification.setUser(postObj.getUser());
        //notification.setMessage("Amount has been Debited");
//        notification.setDate(LocalDate.now());
//        notification.setTime(LocalTime.now());
//        this.notificationRepo.save(notification);

        return transactionObj2;
    }

    @Override
    public List<Transaction> getAllPayments() throws TransactionException {
        if(transactionRepo ==null) throw new TransactionException("Payments not found");
        return this.transactionRepo.findAll();
    }

    @Override
    public Transaction getPaymentById(Integer id) throws TransactionException {
        Optional<Transaction> paymentsOpt = this.transactionRepo.findById(id);
        if(paymentsOpt.isEmpty()) throw new TransactionException("Payment is not found");
        Transaction transaction = paymentsOpt.get();
        return transaction;
    }


}
