package com.miniProject.FundRiseApp.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("user")
    public User createuser(@RequestBody User user){
        System.out.println("controllerworking");
        return this.userService.register(user);
    }


}
