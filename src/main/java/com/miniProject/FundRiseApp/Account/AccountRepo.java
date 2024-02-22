package com.miniProject.FundRiseApp.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account,Integer>{
    List<Account> findByUserId(Integer id);
    List<Account> findByUserName(String accountName);
}
