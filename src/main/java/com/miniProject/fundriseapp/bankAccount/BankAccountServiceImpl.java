package com.miniProject.fundriseapp.bankAccount;

import com.miniProject.fundriseapp.notification.Notification;
import com.miniProject.fundriseapp.notification.NotificationRepo;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepo bankAccountRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private NotificationRepo notificationRepo;

    @Override
    public BankAccount createAccount(BankAccount newBankAccount, Integer userId) throws BankAccountException {
        User user = this.userRepo.findById(userId).get();
        if(user==null) throw new BankAccountException("User not found to add account");
        if(newBankAccount ==null) throw new BankAccountException("Account should not be null");
        newBankAccount.setUser(user);

        Notification notification=new Notification();
        notification.setUser(user);
        notification.setMessage("Your Account has been created");
        notification.setDate(LocalDate.now());
       notification.setTime(LocalTime.now());
       this.notificationRepo.save(notification);
       return this.bankAccountRepo.save(newBankAccount);
    }
    @Override
    public BankAccount getAccountById(Integer userId) throws BankAccountException { // find by userId - exception handling(check user id and account)

        User user = this.userRepo.findById(userId).get();
        if(user==null) throw new BankAccountException("User not found to add account");
         BankAccount bankAccount =this.bankAccountRepo.findByUser(user);

        if(userId!= bankAccount.getId()) throw new BankAccountException("User id doesn't match to get account");
        return bankAccount;
    }


    @Override
    public Boolean updateAccountNameById(Integer accountId, String name) throws BankAccountException {
        BankAccount bankAccount = this.bankAccountRepo.findById(accountId).get();
        if(bankAccount == null)
            throw new BankAccountException("Account id does not exist: "+accountId);
        bankAccount.setAccountName(name);
        if(this.bankAccountRepo.save(bankAccount).getId()==accountId)
            return true;
        return false;
    }

    @Override
    public BankAccount deleteAccountById(Integer accountId) throws BankAccountException {
        Optional<BankAccount> accountOpt = this.bankAccountRepo.findById(accountId);
        if(accountOpt.isEmpty()) throw new BankAccountException("Account not present to delete");
        this.bankAccountRepo.deleteById(accountId);
        Notification notification=new Notification();
        notification.setUser(accountOpt.get().getUser());
        notification.setMessage("Your Account has been Deleted");
        notification.setDate(LocalDate.now());
        notification.setTime(LocalTime.now());
        this.notificationRepo.save(notification);
        return accountOpt.get();
    }


}
