package com.miniProject.fundriseapp.account;

import com.miniProject.fundriseapp.notification.Notification;
import com.miniProject.fundriseapp.notification.NotificationRepo;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private NotificationRepo notificationRepo;

    @Override
    public Account createAccount(Account newAccount, Integer userId) throws AccountException {
        User user = this.userRepo.findById(userId).get();
        newAccount.setUser(user);

        Notification notification=new Notification();
        notification.setUser(user);
        notification.setMessage("Your Account has been created");
        notification.setDate(LocalDate.now());
       notification.setTime(LocalTime.now());
       this.notificationRepo.save(notification);
       return this.accountRepo.save(newAccount);
    }
    @Override
    public Account getAccountById(Integer userId) { // find by userId - exception handling(check user id and account)
        User user = this.userRepo.findById(userId).get();
        return this.accountRepo.findByUser(user);
    }





    @Override
    public Boolean updateAccountNameById(Integer accountId, String name) throws AccountException {
        Account account = this.accountRepo.findById(accountId).get();
        if(account == null)
            throw new AccountException("Account id does not exist: "+accountId);
        account.setAccountName(name);
        if(this.accountRepo.save(account).getId()==accountId)
            return true;
        return false;
    }

    @Override
    public Account deleteAccountById(Integer accountId) {
        Optional<Account> accountOpt = this.accountRepo.findById(accountId);
        this.accountRepo.deleteById(accountId);
        Notification notification=new Notification();
        notification.setUser(accountOpt.get().getUser());
        notification.setMessage("Your Account has been Deleted");
        notification.setDate(LocalDate.now());
        notification.setTime(LocalTime.now());
        this.notificationRepo.save(notification);
        return accountOpt.get();
    }


}
