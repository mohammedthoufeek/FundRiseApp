package com.miniProject.fundriseapp.comment;


import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostRepo;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String createComment(CommentDto commentDto) throws CommentException {
        Post postObj=this.postRepo.findById(commentDto.getPostId()).get();
        User user=this.userRepo.findById(commentDto.getUserId()).get();
        Comment commentObj=new Comment();
        commentObj.setUser(user);
        commentObj.setDate(commentDto.getDate());
        commentObj.setMessage(commentDto.getMessage());
        System.out.println(commentObj+" "+postObj+" "+user);
        Comment commentOb=this.commentRepo.save(commentObj);
        System.out.println(commentObj);
        postObj.getComment().add(commentOb);
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
    public Comment updateComment(Comment comment,Integer userId) throws CommentException {
        Optional<Comment> commentOpt=this.commentRepo.findById(comment.getId());
        User userObj = this.userRepo.findById(userId).orElse(null);
        if(commentOpt.isPresent())
        {
            Notification notification=new Notification();
            notification.setComment(comment);
            notification.setUser(userObj);
            notification.setMessage("Your comment has been updated");
            notification.setDate(LocalDate.now());
            notification.setTime(LocalTime.now());
            this.notificationRepo.save(notification);
            return this.commentRepo.save(comment);
        }
        else throw new CommentException("Comment id is incorrect");
    }

//    @Override
//    public Comment getCommentById(Integer id) throws CommentException {
//        Optional <Comment> commentOpt=this.commentRepo.findById(id);
//        if(commentOpt.isEmpty()) throw new CommentException("Enter correct id to find the comment");
//        return this.commentRepo.findById(id).get();
//    }

//    @Override
//    public Comment updateComment(Comment comment) throws CommentException {
//        Optional<Comment> commentOpt=this.commentRepo.findById(comment.getId());
//        if(commentOpt.isPresent()) return this.commentRepo.save(comment);
//        else throw new CommentException("Comment id is incorrect");
//    }

    @Override
    public Comment deleteCommentById(Integer id) throws CommentException {

        Optional<Comment> commentOpt=this.commentRepo.findById(id);
        if (commentOpt.isPresent()) {
            this.commentRepo.deleteById(id);
            return commentOpt.get();
        }
        else throw new CommentException("Given id is incorrect to delete");
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
