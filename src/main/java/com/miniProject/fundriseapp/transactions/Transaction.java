package com.miniProject.fundriseapp.transactions;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Transaction {
   @Id
    @GeneratedValue
    private Integer id;
    @NotNull(message = "title Field shouldnot be blank")
    private double amount;
   @NotNull(message = "Date of birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private LocalTime time;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Transaction(Integer id, double amount, LocalDate date, LocalTime time) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public Transaction(double amount, LocalDate date, LocalTime time, User user, Post post) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.user = user;
        this.post = post;
    }

    public Transaction() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", time=" + time +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
