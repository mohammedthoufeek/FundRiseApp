package com.miniProject.fundriseapp.bankAccount;
import com.miniProject.fundriseapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount,Integer>{
    // Account findByUser(Integer id);

    BankAccount findByUser(User user);
    List<BankAccount> findByUserName(String accountName);

}