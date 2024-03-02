package com.miniProject.fundriseapp.account;

import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostException;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
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
        String hashedCvv = BCrypt.hashpw(newAccount.getCvv(),BCrypt.gensalt());
        newAccount.setCvv(hashedCvv);
        newAccount.setUser(user);
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


    /*public Boolean updateAccountNameById(Integer accountId, Account account) throws AccountException {
        Account account1 = this.accountRepo.findById(accountId).get();
        if(account1 == null)
            throw new AccountException("Account id does not exist: "+accountId);
        account1.setAccountName(account1.getAccountName());
        if(this.accountRepo.save(account1).getId()==accountId)
            return true;
        return false;
    }*/
    @Override
    public Account updateAccountNameById(Integer accountId, Account account)throws AccountException {
        Optional<Account> accountOpt =this.accountRepo.findById(account.getId());
        if(accountOpt.isPresent()) return this.accountRepo.save(account);
        else throw new AccountException("Post not found to update");
    }


    @Override
    public Account deleteAccountById(Integer accountId) {
        Optional<Account> accountOpt = this.accountRepo.findById(accountId);
        this.accountRepo.deleteById(accountId);
        return accountOpt.get();
    }


}