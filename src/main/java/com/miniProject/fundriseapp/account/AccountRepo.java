package com.miniProject.fundriseapp.account;
import com.miniProject.fundriseapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account,Integer>{
    // Account findByUser(Integer id);

    Account findByUser(User user);
    List<Account> findByUserName(String accountName);
    Optional<Account> findByAccountNumber(Integer accountNumber);
}