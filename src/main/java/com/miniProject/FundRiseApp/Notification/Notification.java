package com.miniProject.FundRiseApp.Notification;

import com.miniProject.FundRiseApp.Post.Post;
import com.miniProject.FundRiseApp.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    private Post post;
   private  String message;
    private LocalDate date;
    private LocalTime time;

    public Notification() {
    }

    public Notification(Integer id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

    public Notification(Integer id, User user, Post post, String message, LocalDate date, LocalTime time) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
