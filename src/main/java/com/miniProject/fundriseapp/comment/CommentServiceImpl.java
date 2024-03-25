package com.miniProject.fundriseapp.comment;


import com.miniProject.fundriseapp.notification.Notification;
import com.miniProject.fundriseapp.notification.NotificationRepo;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostRepo;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private NotificationRepo notificationRepo;
    @Override
    public String createComment(CommentDto commentDto) throws CommentException {
        Post postObj=this.postRepo.findById(commentDto.getPostId()).get();
        if(postObj==null) throw new CommentException("Post not present to comment");
        User user=this.userRepo.findById(commentDto.getUserId()).get();
        if(user==null) throw new CommentException("User is not present to comment");
        Comment commentObj=new Comment();
        commentObj.setUser(user);
        commentObj.setDate(commentDto.getDate());
        commentObj.setMessage(commentDto.getMessage());
//        System.out.println(commentObj+" "+postObj+" "+user);
        Comment commentOb=this.commentRepo.save(commentObj);
        if(commentOb==null) throw new CommentException("Comment is not saved");
        System.out.println(commentObj);
        postObj.getComment().add(commentOb);


        // sending notification to user

        Notification notification=new Notification();
        notification.setComment(commentObj);
        notification.setUser(user);
        notification.setMessage("You have Commented \" "+commentObj.getMessage()+"\", for post titled as "+postObj.getTitle()+", for cause of "+postObj.getCause()+" is done.");
        notification.setDate(LocalDate.now());
        notification.setTime(LocalTime.now());
        this.notificationRepo.save(notification);
        this.postRepo.save(postObj);
        return "Comment Created Successfull";
    }


    @Override
    public Comment getCommentById(Integer id) throws CommentException {
        Optional <Comment> commentOpt=this.commentRepo.findById(id);
        if(commentOpt.isEmpty()) throw new CommentException("Enter correct id to find the comment");
        return this.commentRepo.findById(id).get();
    }

    @Override
    public Comment updateComment(Comment comment,Integer userId,Integer postId) throws CommentException {
        Optional<Comment> commentOpt=this.commentRepo.findById(comment.getId());
        if(commentOpt==null) throw new CommentException("Comment will not be updated");
        User userObj = this.userRepo.findById(userId).orElse(null);
        if(userObj==null) throw new CommentException("User not present to update comment");
        Post postObj=this.postRepo.findById(postId).get();
        if(commentOpt.isPresent())
        {
            Comment commentObj=this.commentRepo.findById(comment.getId()).get();
            Notification notification=new Notification();
            notification.setComment(comment);
            notification.setUser(userObj);
            notification.setMessage("You have updated a Comment \" "+commentObj.getMessage()+"\",on Post titled as \""+postObj.getTitle()+"\"");
            notification.setDate(LocalDate.now());
            notification.setTime(LocalTime.now());
            this.notificationRepo.save(notification);
            return this.commentRepo.save(comment);
        }
        else throw new CommentException("Comment id is incorrect");
    }

    @Override
    public Comment deleteCommentById(Integer id,Integer userId,Integer postId) throws CommentException {

        Optional<Comment> commentOpt=this.commentRepo.findById(id);
        if(commentOpt==null) throw new CommentException("Comment id is not present to delete");
        User userObj = this.userRepo.findById(userId).orElse(null);
        Post postObj=this.postRepo.findById(postId).get();
        if(userObj==null) throw new CommentException("User not present to delete comment");
        if (commentOpt.isPresent()) {
            Notification notification=new Notification();
            Comment retrievedComment = commentOpt.get();
            notification.setComment(retrievedComment);
            notification.setUser(userObj);
            notification.setMessage("Your Comment \" "+retrievedComment.getMessage()+"\",on Post titled as \""+postObj.getTitle()+"\" is deleted.");
            notification.setDate(LocalDate.now());
            notification.setTime(LocalTime.now());
            this.notificationRepo.save(notification);
            this.commentRepo.deleteById(id);
            return commentOpt.get();
        }
        else throw new CommentException("Given id is incorrect to delete comment");
    }

    @Override
    public List<Comment> getAllComments() throws CommentException {
        List<Comment> commentOpt=this.commentRepo.findAll();
        if(commentOpt.isEmpty()) throw new CommentException("Please Create some Comment to view!!!");
        return this.commentRepo.findAll();
    }

    @Override
    public Comment updateMessage(Integer commentId, String message) throws CommentException {
        Optional<Comment> commentOpt=commentRepo.findById(commentId);
        if(commentOpt.isPresent()) throw new CommentException("Comment Id is not wrong");
        Comment comment=commentOpt.get();
        comment.setMessage(message);
        return this.commentRepo.save(comment);
    }


}