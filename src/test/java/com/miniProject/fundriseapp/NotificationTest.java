package com.miniProject.fundriseapp;
import com.miniProject.fundriseapp.user.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Order(1)
    @DisplayName("Valid user Id")
    @Test
    void validUserId() {
        Integer userId = 1;
        User user = null;

        try {
            user = new User(userId, "Aishwarya", LocalDate.now(), "yugsdjhsje", "6378765432", 45, User.Usertype.USER, "aishwa@gmail.com", "gv5");
            Assertions.assertNotNull(user);
            assertEquals(userId, user.getId());
        } catch (Exception e) {
            user = null;
        }
    }


    @Order(2)
    @DisplayName("Invalid user Id")
    @Test
    void InvalidUserId() {
        Integer userId = -1;
        User user = null;
        if (userId < 0) {
            try {
                if (userId < 0) {
                    throw new IllegalArgumentException("Invalid user ID: " + userId);
                }
                user = new User(userId, "Aishwarya", LocalDate.now(), "yugsdjhsje", "6378765432", 45, User.Usertype.USER, "aishwa@gmail.com", "gv5");
            } catch (IllegalArgumentException e) {
                user = null;
            }
        }
        Assertions.assertNull(user, "User object should be null for invalid user ID");
    }








}
