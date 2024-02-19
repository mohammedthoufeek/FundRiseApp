package com.miniProject.FundRiseApp.User;

<<<<<<< HEAD
public class UserServiceImpl {
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public User register(User user) {
        System.out.println("service working");
        return this.userRepo.save(user);
    }
>>>>>>> sanjay
}
