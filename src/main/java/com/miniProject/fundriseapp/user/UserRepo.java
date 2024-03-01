package com.miniProject.fundriseapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByEmail(String email);

    User findByPassword(String password);

    User findByEmailAndPassword(String email, String password);
   List<User> findByUsertype(User.Usertype usertype);


}
