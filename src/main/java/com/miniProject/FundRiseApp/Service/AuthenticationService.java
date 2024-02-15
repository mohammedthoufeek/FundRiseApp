package com.miniProject.FundRiseApp.Service;

import com.miniProject.FundRiseApp.Entity.User;

public interface AuthenticationService {
    User login(String emailId, String Password);
    User forgotPassword(String emailId);
}
