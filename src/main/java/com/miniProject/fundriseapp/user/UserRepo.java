package com.miniProject.fundriseapp.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByEmail(String email);

    User findByPassword(String password);

    User findByEmailAndPassword(String email, String password);
   List<User> findByUsertype(User.Usertype usertype);

}
