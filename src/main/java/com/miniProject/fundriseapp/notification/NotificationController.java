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
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @Autowired
    UserRepo userRepo;

    @GetMapping("Notifications/{userId}")
    public List<Notification> getAllNotificationByUserId(@PathVariable Integer userId) throws NotificationException {
        return this.notificationService.getAllNotificationByTheirUserId(userId);
    }
    @PostMapping("/sendNotificationToAllExceptPublisher")
    public String sendNotificationToAllExceptPublisher(@RequestParam Integer publisherId, @RequestParam String message) {
        User publisher = userRepo.findById(publisherId).orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
        notificationService.sendNotificationToAllUsersExceptPublisher(publisher, message);
        return "Notification sent to all users except the publisher";
    }

}
