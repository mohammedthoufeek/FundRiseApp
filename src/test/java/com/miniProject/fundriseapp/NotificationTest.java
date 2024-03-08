package com.miniProject.fundriseapp;

import com.miniProject.fundriseapp.account.Account;
import com.miniProject.fundriseapp.notification.Notification;
import com.miniProject.fundriseapp.notification.NotificationException;
import com.miniProject.fundriseapp.notification.NotificationService;
import com.miniProject.fundriseapp.notification.NotificationServiceImpl;
import com.miniProject.fundriseapp.payments.Payments;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.user.*;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NotificationTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName(value = "Should_createNew User")
    public void createUserTest(){
        try {
            Assertions.assertNotNull(userService.register(new User(1, "Aishwarya", LocalDate.now(), "yugsdjhsje", "6378765432", 45,User.Usertype.valueOf("USER"), "aishwa@gamil.com", "gv5") ) );

        }  catch (UserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void createUserOfNullUserShouldThrowExceptionTest(){
         userService=new UserServiceImpl();
        Assertions.assertThrows(UserException.class,
                ()->  userService.register(null) );

    }







}
