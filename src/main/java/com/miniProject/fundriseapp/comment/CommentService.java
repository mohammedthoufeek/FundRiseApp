package com.miniProject.fundriseapp.comment;

import java.util.List;

public interface CommentService {
    Comment getCommentById(Integer id ) throws CommentException;
    Comment updateComment(Comment comment,Integer userId,Integer postId) throws CommentException;
    Comment deleteCommentById(Integer id,Integer userId,Integer postId) throws CommentException;
    List<Comment> getAllComments() throws CommentException;
    Comment updateMessage(Integer commentId, String message)throws CommentException;
    String createComment(CommentDto commentDto) throws CommentException;
}