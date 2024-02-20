package com.miniProject.fundriseapp.user;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("user")
    public User createuser(@RequestBody User user) throws UserException {
        return this.userService.register(user);
    }

    @PostMapping("signin")
    public Integer signIn(@RequestBody SignInRequest signInRequest, HttpSession httpSession) throws UserException {
        return this.userService.signIn(signInRequest,httpSession);
    }
    @GetMapping("signout")
    public String signOut(HttpSession httpSession) {
        return this.userService.signOut(httpSession);
    }
    @GetMapping("userid")
    public Integer getUserId(HttpSession httpSession) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId != null) {
            return userId;
        } else {
            return null; // or throw exception or handle as required
        }
    }
    @GetMapping("profile")
    public User getProfile(HttpSession httpSession) throws UserException {
        return this.userService.getProfile(httpSession);
    }
    @GetMapping("profiles")
    public List<User> getProfile() throws UserException {
        return this.userService.getProfiles();
    }
    @GetMapping("profiles/charity")
    public List<User> getProfilecharity() throws UserException {
        return this.userService.getProfilecharity();
    }
    @GetMapping("profiles/investors")
    public List<User> getProfilinvestors() throws UserException {
        return this.userService.getProfileInvestors();
    }
    @GetMapping("profiles/users")
    public List<User> getProfileusers() throws UserException {
        return this.userService.getProfileUsers();
    }


    }

