package com.miniProject.fundriseapp.notification;

import com.miniProject.fundriseapp.comment.Comment;
import com.miniProject.fundriseapp.comment.CommentException;
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

    @Override
    public void sendNotificationToAllUsersExceptPublisher(User user, String message) {
        List<User> allUsers = userRepo.findAll();
        allUsers.remove(user);
        allUsers.forEach(users -> {
            Notification notification = new Notification();
            notification.setMessage("Notification from"+ user.getName()+"(Id: " + user.getId() + ") " + message);
            notification.setUser(users);
            notificationRepo.save(notification);
        });
    }
}
