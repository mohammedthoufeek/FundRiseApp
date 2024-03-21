package com.miniProject.fundriseapp.notification;

import com.miniProject.fundriseapp.comment.Comment;
import com.miniProject.fundriseapp.comment.CommentException;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostException;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @Autowired
    UserRepo userRepo;

    @GetMapping("Notification/{userId}")
    public List<Notification> getAllNotificationByUserId(@PathVariable Integer userId) throws NotificationException {
        return this.notificationService.getAllNotificationByTheirUserId(userId);
    }
    @PostMapping("Notification/{userId}/{postId}")
    public Integer sendNotificationToAllUsersExceptPublisher(@PathVariable  Integer userId,@PathVariable Integer postId) throws NotificationException {
        return this.notificationService.sendNotificationToAllUsersExceptPublisher(userId,postId);
    }


}
