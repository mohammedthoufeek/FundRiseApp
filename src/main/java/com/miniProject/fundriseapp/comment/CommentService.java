package com.miniProject.fundriseapp.comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    List<Comment> getCommentById(Integer id ) throws CommentException;

    List<Comment> getAllComments() throws CommentException;
    Comment updateMessage(Integer commentId, String message)throws CommentException;
    Map<String, String> createComment(CommentDto commentDto) throws CommentException;
    Comment updateComment(Comment comment,Integer userId,Integer postId)throws CommentException;
    Comment deleteCommentById(Integer id,Integer userId,Integer postId) throws CommentException;
}