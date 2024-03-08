package com.miniProject.fundriseapp.account;

import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepo accountRepo;
    private UserRepo userRepo;

    @Override
    public Account createAccount(Account newAccount, Integer userId) throws AccountException {

        Account accountOpt = this.accountRepo.findById(newAccount.getId()).get();
        User user = this.userRepo.findById(userId).get();
        if(accountOpt != null)
            throw new AccountException("Id already exists :"+newAccount.getId());
        newAccount.setUser(user);
        //accountOpt = accountRepo.save(newAccount);
        //user.setAccountDetails(accountOpt);
        return this.accountRepo.save(newAccount);
    }

    @Override
    public Account getAccountById(Integer userId) { // find by userId - exception handling(check user id and account)
        User user = this.userRepo.findById(userId).get();
        return this.accountRepo.findByUser(user);
    }

    @Override
    public Double depositFundsById(Integer accountId, Double amount) throws AccountException {
        return null;
    }

    /*@Override
    public Double depositFundsById(Integer accountId, Double amount) throws AccountException {
        Optional<Account> accountOpt = this.accountRepo.findById(accountId);
        if(accountOpt.isEmpty())
            throw new AccountException("Account does not exist: "+accountId);
        Account account = accountOpt.get();
        amount = account.getBalance()+amount;
        account.setBalance(amount);
        return amount;
    }
*/
   /* @Override
    public Double withdrawAllFunds(Integer accountId) {

        return null;
    } */

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
        return accountOpt.get();
    }


}
