package com.miniProject.fundriseapp.comment;

import java.time.LocalDate;

public class CommentDto {
    private Integer postId;
    private Integer userId;
    private String message;
    private LocalDate date;

    public CommentDto() {
    }

    public CommentDto(Integer postId, Integer userId, String message, LocalDate date) {
        this.postId = postId;
        this.userId = userId;
        this.message = message;
        this.date = date;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
