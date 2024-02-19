package com.miniProject.fundriseapp.comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("comment")
    public Comment createComment(@RequestBody Comment comment) throws CommentException{
        return this.commentService.createComment(comment);
    }

    @PutMapping("comment")
    public Comment updateComment(@RequestBody Comment comment) throws CommentException{
        return this.commentService.updateComment(comment);
    }

    @GetMapping("comment/{id}")
    public Comment getCommentById(@PathVariable Integer id) throws CommentException{
        return this.commentService.getCommentById(id);
    }

    @GetMapping("comments")
    public List<Comment> getAllComments() throws CommentException{
        return this.commentService.getAllComments();
    }

    @DeleteMapping("comment/{id}")
    public Comment deleteCommentById(@PathVariable Integer id) throws CommentException{
        return this.commentService.deleteCommentById(id);
    }

}
