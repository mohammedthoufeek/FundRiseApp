package com.miniProject.fundriseapp.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CommentDto {
    @NotBlank(message = "postid shouldnot be blank")
    private Integer postId;
    @NotBlank(message = "userid shouldnot be blank")
    private Integer userId;
    @NotBlank(message = "message shouldnot be blank")
    private String message;
    @NotNull(message = "Date of birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
