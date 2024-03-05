package com.miniProject.fundriseapp.account;

import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public Account createAccount(Account newAccount, Integer userId) throws AccountException {
        User user = this.userRepo.findById(userId).get();
        newAccount.setUser(user);
        return this.accountRepo.save(newAccount);
    }
    @Override
    public Account getAccountById(Integer userId) throws AccountException { // find by userId - exception handling(check user id and account)

        User user = this.userRepo.findById(userId).get();
         Account account=this.accountRepo.findByUser(user);
        if(userId!=account.getId()) throw new AccountException("User id doesn't match to get account");
        return account;
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
    public Account deleteAccountById(Integer accountId) throws AccountException {
        Optional<Account> accountOpt = this.accountRepo.findById(accountId);
        if(accountOpt.isEmpty()) throw new AccountException("Account not present to delete");
        this.accountRepo.deleteById(accountId);
        return accountOpt.get();
    }


}
