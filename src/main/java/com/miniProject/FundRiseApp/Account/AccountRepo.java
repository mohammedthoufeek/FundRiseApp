package com.miniProject.FundRiseApp.Account;

import com.miniProject.FundRiseApp.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account,Integer>{
   // Account findByUser(Integer id);

    Account findByUser(User user);
    List<Account> findByUserName(String accountName);
}
