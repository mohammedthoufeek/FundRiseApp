package com.miniProject.fundriseapp.post;


import com.miniProject.fundriseapp.comment.CommentRepo;
import com.miniProject.fundriseapp.notification.Notification;
import com.miniProject.fundriseapp.notification.NotificationRepo;

import com.miniProject.fundriseapp.notification.NotificationService;
import com.miniProject.fundriseapp.notification.NotificationServiceImpl;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
        @Autowired
        private PostRepo postrepo;
    @Autowired
        private CommentRepo commentRepo;
    @Autowired
        private UserRepo userRepo;
    @Autowired
    private NotificationRepo notificationRepo;



    @Override
    public Post createPost(Integer userId, Post newPost) throws PostException{

        User userObj = this.userRepo.findById(userId).orElse(null);
        if(userObj==null) throw new PostException("User can't be null");
        if (userObj != null) {
            // Set the user for the new post
            newPost.setUser(userObj);
            // Save the post
            Post postObj = this.postrepo.save(newPost);
            if(postObj==null) throw new PostException("Post is not created");

            // Add the post to the user's list of posts
            userObj.getPost().add(postObj);
            // Save the user object
            this.userRepo.save(userObj);
            String message="post";


            Notification notification=new Notification();
            notification.setPost(postObj);
            notification.setUser(userObj);
            notification.setMessage("Your Post has been published");
            notification.setDate(LocalDate.now());
            notification.setTime(LocalTime.now());
            this.notificationRepo.save(notification);

            return postObj;
        } else {
            throw new PostException("User not found");
        }
    }

    @Override
    public Post getPostById(Integer id)throws PostException {
        Optional<Post> postOpt=this.postrepo.findById(id);
        if(postOpt==null) throw new PostException("Post is not available 'By id'");
        if(postOpt.isPresent()) {
//            System.out.println("getpost"+ this.postrepo.findById(id).get());
            return this.postrepo.findById(id).get();
        }
        else throw new PostException("No post was created yet!!!");
    }



    @Override
    public Post updatePost(Post post,Integer userId)throws PostException {
        Optional<Post> postOpt =this.postrepo.findById(post.getId());
        if(postOpt==null) throw new PostException("Post is not available 'By Update post'");
        User userObj = this.userRepo.findById(userId).orElse(null);
        if(userObj==null) throw new PostException("Enter correct user id to update");
        if(postOpt.isPresent())
        {

            Notification notification=new Notification();
            notification.setPost(getPostById(post.getId()));
            notification.setUser(userObj);
            notification.setMessage("Your post has been updated");
            notification.setDate(LocalDate.now());
            notification.setTime(LocalTime.now());
            this.notificationRepo.save(notification);
            return this.postrepo.save(post);
        }
        else throw new PostException("Post not found to update");
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
            //notification.getPost(postOpt);

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
    public List<Post> getPostByUserId(Integer userId)throws PostException {
        User userObj=this.userRepo.findById(userId).get();
        List<Post> posts=this.postrepo.findByUser(userObj);
        if(posts==null) throw new PostException("Post is not available for userId");
        return posts;
    }



}
