package com.miniProject.FundRiseApp.Service;


import com.miniProject.FundRiseApp.Entity.User;

public interface UserService {
    User register(User user);
    User viewProfile(long id);
    User updateProfile(User user);
    User deleteProfile(User user);
}
