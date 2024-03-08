package com.miniProject.fundriseapp.payments;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class PaymentDto {
    @NotBlank(message = "userid Field shouldnot be blank")
    private Integer userId;
    @NotBlank(message = "postid Field shouldnot be blank")
    private Integer postId;
    @NotBlank(message = "amount Field shouldnot be blank")
    private Double amount;
    @NotNull(message = "Date of birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private LocalTime time;

    public PaymentDto() {
    }

    public PaymentDto(Integer userId, Integer postId, Double amount, LocalDate date, LocalTime time) {
        this.userId = userId;
        this.postId = postId;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}