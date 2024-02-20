package com.miniProject.fundriseapp.comment;

public class CommentDto {
    private Integer postId;
    Comment comment;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
