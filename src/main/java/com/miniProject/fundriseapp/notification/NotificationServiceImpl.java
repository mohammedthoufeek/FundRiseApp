package com.miniProject.fundriseapp.notification;

import com.miniProject.fundriseapp.comment.Comment;
import com.miniProject.fundriseapp.comment.CommentException;
import com.miniProject.fundriseapp.post.PostRepo;
import com.miniProject.fundriseapp.user.UserRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.ErrorManager;

import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.post.Post;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepo notificationRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;


    //    @Override
//    public List<Notification> getAllNotification() throws NotificationException {
//    List<Notification> NotificationOpt=this.notificationRepo.findAll();
//    if(NotificationOpt.isEmpty()) throw new NotificationException("You haven't received any Notification yet!!!");
//    return this.notificationRepo.findAll();
//}
    @Override
    public List<Notification> getAllNotificationByTheirUserId(Integer userId) throws NotificationException {

        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) {
            throw new NotificationException("User with ID " + userId + " not found");
        }
        List<Notification> notifications = notificationRepo.findByUserId(userId);
        if (notifications.isEmpty()) {
            throw new NotificationException("No notifications found for user with ID: " + userId);
        }
        return notifications;
    }


public Integer sendNotificationToAllUsersExceptPublisher(Integer userId, Integer postId) {
    User userObj = this.userRepo.findById(userId).get();
    Post postObj = this.postRepo.findById(postId).get();
    List<User> allUsers = userRepo.findAll();
    allUsers.remove(userObj);
    allUsers.forEach(users -> {
        Notification notification = new Notification();
        notification.setUser(users);
        notification.setPost(postObj);
        notification.setTime(LocalTime.now());
        notification.setDate(LocalDate.now());

        notification.setMessage("Notification from \"" + userObj.getName() + "\"" + ": I am raising fund for " + postObj.getTitle() );

        notificationRepo.save(notification);
    });

    return allUsers.size();
}
}
