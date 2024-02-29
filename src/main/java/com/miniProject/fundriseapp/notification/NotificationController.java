package com.miniProject.fundriseapp.notification;

import com.miniProject.fundriseapp.comment.Comment;
import com.miniProject.fundriseapp.comment.CommentException;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostException;
import com.miniProject.fundriseapp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("Notifications/{userId}")
    public List<Notification> getAllNotificationByUserId(@PathVariable Integer userId) throws NotificationException {
        return this.notificationService.getAllNotificationByTheirUserId(userId);
    }


}
