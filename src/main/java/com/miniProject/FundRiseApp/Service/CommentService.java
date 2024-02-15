package com.miniProject.FundRiseApp.Service;


import com.miniProject.FundRiseApp.Entity.Comment;

public interface CommentService {
    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
    Comment deleteComment(long id);

}
