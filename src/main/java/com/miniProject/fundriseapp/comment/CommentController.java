package com.miniProject.fundriseapp.comment;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

@CrossOrigin("http://localhost:4200/")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("comment")
    public Map<String, String> createComment(@Valid @RequestBody CommentDto commentDto) throws CommentException{
        return this.commentService.createComment(commentDto);
    }


//    @PutMapping("comment")
//    public Comment updateComment(@RequestBody Comment comment) throws CommentException{
//        return this.commentService.updateComment(comment);
//    }

    @PutMapping("comment")
    public Comment updateComment(@Valid @RequestBody Comment comment,Integer userId) throws CommentException{
        return this.commentService.updateComment(comment,userId);
    }


    @PatchMapping("comment")
    public Comment updateMessage(@RequestBody Integer commentId, String message) throws CommentException{
        return this.commentService.updateMessage(commentId,message);
    }

    @GetMapping("comment/{id}")
    public List<Comment> getCommentById(@PathVariable Integer id) throws CommentException{
        return this.commentService.getCommentById(id);
    }
//
//    @GetMapping("comments")
//    public List<Comment> getAllComments() throws CommentException{
//        return this.commentService.getAllComments();
//    }

    @DeleteMapping("comment/{id}")
    public Comment deleteCommentById(@PathVariable Integer id,Integer userId) throws CommentException{
        return this.commentService.deleteCommentById(id,userId);
    }

}
