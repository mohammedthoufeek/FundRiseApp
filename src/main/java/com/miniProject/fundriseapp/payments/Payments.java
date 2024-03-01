package com.miniProject.fundriseapp.payments;

import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Payments {
   @Id
    //@GeneratedValue
    private Integer id;
    private double amount;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Payments(Integer id, double amount, LocalDate date, LocalTime time) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public Payments(Integer id, double amount, LocalDate date, LocalTime time, User user, Post post) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.user = user;
        this.post = post;
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
