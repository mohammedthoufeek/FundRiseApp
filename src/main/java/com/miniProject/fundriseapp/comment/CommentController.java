package com.miniProject.fundriseapp.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("comment")
    public String createComment(@RequestBody CommentDto commentDto) throws CommentException{
        return this.commentService.createComment(commentDto);
    }


    @PutMapping("comment")
    public Comment updateComment(@RequestBody Comment comment,Integer userId) throws CommentException{
        return this.commentService.updateComment(comment,userId);
    }

//    @PutMapping("comment")
//    public Comment updateComment(@RequestBody Comment comment) throws CommentException{
//        return this.commentService.updateComment(comment);
//    }

    @PatchMapping("comment")
    public Comment updateMessage(@RequestBody Integer commentId, String message) throws CommentException{
        return this.commentService.updateMessage(commentId,message);
    }

//    @GetMapping("comment/{id}")
//    public Comment getCommentById(@PathVariable Integer id) throws CommentException{
//        return this.commentService.getCommentById(id);
//    }

//    @GetMapping("comments")
//    public List<Comment> getAllComments() throws CommentException{
//        return this.commentService.getAllComments();
//    }

    @DeleteMapping("comment/{id}")
    public Comment deleteCommentById(@PathVariable Integer id,Integer userId) throws CommentException{
        return this.commentService.deleteCommentById(id,userId);
    }

}
