package com.miniProject.fundriseapp.comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public Comment createComment(Comment comment) throws CommentException {
        Optional<Comment> commentOpt=this.commentRepo.findById(comment.getId());
        if(commentOpt.isPresent()) throw new CommentException("Comment is already exists");
        return this.commentRepo.save(comment);
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
}
