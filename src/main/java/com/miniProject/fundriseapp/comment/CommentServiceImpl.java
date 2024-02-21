package com.miniProject.fundriseapp.comment;


import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepo commentRepo;
    private PostRepo postRepo;
    @Override
    public String createComment(Integer postId,Comment comment) throws CommentException {
        Optional<Comment> commentOpt=this.commentRepo.findById(comment.getId());
        if(commentOpt.isPresent()) throw new CommentException("Comment is already exists");
        Comment commentObj= this.commentRepo.save(comment);
        Post postObj=this.postRepo.findById(postId).get();
        postObj.getComment().add(commentObj);
        this.postRepo.save(postObj);
        commentObj.setPost(postObj);
        commentRepo.save(commentObj);
        return "Comment Created Successfull";

    }

    @Override
    public Comment getCommentById(Integer id) throws CommentException {
        Optional <Comment> commentOpt=this.commentRepo.findById(id);
        if(commentOpt.isEmpty()) throw new CommentException("Enter correct id to find the comment");
        return this.commentRepo.findById(id).get();
    }

    @Override
    public Comment updateComment(Comment comment) throws CommentException {
        Optional<Comment> commentOpt=this.commentRepo.findById(comment.getId());
        if(commentOpt.isPresent()) return this.commentRepo.save(comment);
        else throw new CommentException("Comment id is incorrect");
    }

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
