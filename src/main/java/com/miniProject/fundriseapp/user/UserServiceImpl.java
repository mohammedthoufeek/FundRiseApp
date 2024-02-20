package com.miniProject.fundriseapp.user;


import com.miniProject.fundriseapp.comment.Comment;
import com.miniProject.fundriseapp.comment.CommentRepo;
import com.miniProject.fundriseapp.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    private CommentRepo commentRepo;
    @Override
    public User register(User user) {
        System.out.println("service working");
        return this.userRepo.save(user);

    }


}
