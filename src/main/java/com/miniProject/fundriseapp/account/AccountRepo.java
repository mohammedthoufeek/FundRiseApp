package com.miniProject.fundriseapp.account;
import com.miniProject.fundriseapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepo extends JpaRepository<Account,Integer>{
    // Account findByUser(Integer id);

    Account findByUser(User user);
    List<Account> findByUserName(String accountName);
}