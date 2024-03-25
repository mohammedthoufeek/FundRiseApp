package com.miniProject.fundriseapp.comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    List<Comment> getCommentById(Integer id ) throws CommentException;
    Comment updateComment(Comment comment,Integer userId) throws CommentException;
    Comment deleteCommentById(Integer id,Integer userId) throws CommentException;

    List<Comment> getAllComments() throws CommentException;
    Comment updateMessage(Integer commentId, String message)throws CommentException;
    Map<String, String> createComment(CommentDto commentDto) throws CommentException;
}