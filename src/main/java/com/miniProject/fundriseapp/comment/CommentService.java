package com.miniProject.fundriseapp.comment;

import java.util.List;

public interface CommentService {
    Comment getCommentById(Integer id ) throws CommentException;
    Comment updateComment(Comment comment) throws CommentException;
    Comment deleteCommentById(Integer id) throws CommentException;
    List<Comment> getAllComments() throws CommentException;
    Comment updateMessage(Integer commentId, String message)throws CommentException;
    String createComment(CommentDto commentDto) throws CommentException;
}
