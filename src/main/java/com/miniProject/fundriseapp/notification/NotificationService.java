package com.miniProject.fundriseapp.notification;

import com.miniProject.fundriseapp.comment.Comment;
import com.miniProject.fundriseapp.comment.CommentException;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NotificationService {

    List<Notification>getAllNotificationByTheirUserId(Integer userId) throws NotificationException;
    public Integer sendNotificationToAllUsersExceptPublisher(Integer userId,Integer postId);
}
