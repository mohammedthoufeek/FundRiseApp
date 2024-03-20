package com.miniProject.fundriseapp.post;


import com.miniProject.fundriseapp.comment.CommentRepo;
import com.miniProject.fundriseapp.notification.Notification;
import com.miniProject.fundriseapp.notification.NotificationRepo;

import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postrepo;
    @Autowired
        private UserRepo userRepo;
    @Autowired
    private NotificationRepo notificationRepo;



    @Override
    public Post createPost(Integer userId, Post newPost) throws PostCreationException{
        User userObj = this.userRepo.findById(userId).orElse(null);
        if(userObj==null) throw new PostCreationException("User can't be null");

        // Save the post
        Post postObj = this.postrepo.save(newPost);
        if(postObj==null) throw new PostCreationException("Post is not created");

        // Set the user for the new post
        newPost.setUser(userObj);

        // Add the post to the user's list of posts
        userObj.getPost().add(postObj);

        // Save the user object
        this.userRepo.save(userObj);


        Notification notification=new Notification();
        notification.setPost(postObj);
        notification.setUser(userObj);
        notification.setMessage("Your Post has been published");
        notification.setDate(LocalDate.now());
        notification.setTime(LocalTime.now());
        this.notificationRepo.save(notification);

        return newPost;
    }

    @Override
    public Post getPostById(Integer id)throws PostException {
        Optional<Post> postOpt=this.postrepo.findById(id);
        if(postOpt==null) throw new PostException("Post is not available 'For this id'");
        return postOpt.get();
    }



    @Override
    public Post updatePost(Post post,Integer userId)throws PostException {
        Optional<Post> postOpt =this.postrepo.findById(post.getId());
        if(postOpt.isPresent()) throw new PostException("Post is not available 'To Update post'");
        User userObj = this.userRepo.findById(userId).orElse(null);
        if(userObj==null) throw new PostException("Enter correct user id 'to update the post'");

        Notification notification=new Notification();
        notification.setPost(getPostById(post.getId()));
        notification.setUser(userObj);
        notification.setMessage("Your post has been updated");
        notification.setDate(LocalDate.now());
        notification.setTime(LocalTime.now());
        this.notificationRepo.save(notification);
        return this.postrepo.save(post);

    }

    @Override
    public Post deletePostById(Integer id, Integer userId)throws PostException {
        Optional<Post> postOpt=this.postrepo.findById(id);
        if (postOpt.isPresent()) {
            this.postrepo.deleteById(id);
            User userObj = this.userRepo.findById(userId).orElse(null);
            //Sending Notification to user after deleting the post
            Notification notification=new Notification();
            notification.setUser(userObj);
            notification.setMessage("Your post has been deleted");
            notification.setDate(LocalDate.now());
            notification.setTime(LocalTime.now());
            this.notificationRepo.save(notification);

            return postOpt.get();
        }

        else throw new PostException("Post Id you entered is incorrect");
    }

    @Override
    public List<Post> getAllPost()throws PostException {
        List<Post> postOpt=this.postrepo.findAll();
        if(postOpt.isEmpty()) throw new PostException("No post was created");

        return this.postrepo.findAll();
    }

    @Override
    public List<Post> getAllPostByUserId(Integer userId) throws PostException {

        User userobj=this.userRepo.findById(userId).get();
        if(userobj==null) throw new PostException("User not found");
        return this.postrepo.findByUser(userobj);
    }


}
