package com.miniProject.fundriseapp.comment;

import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;

    private String message;
//    private LocalTime time;
    private LocalDate date;
    @OneToOne
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {
//        this.time=LocalTime.now();
        this.date=LocalDate.now();
    }

    public Comment(Integer id, String message, LocalTime time, LocalDate date) {
        this.id = id;
        this.message = message;
//        this.time = LocalTime.now();
        this.date = LocalDate.now();
    }

    public Comment(Integer id, String message, User user, Post post) {
        this.id = id;
        this.message = message;
//        this.time = LocalTime.now();
        this.date = LocalDate.now();
        this.user = user;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public LocalTime getTime() {
//        return time;
//    }
//
//    public void setTime(LocalTime time) {
//        this.time = time;
//    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
        return "Comment{" +
                "id=" + id +
                ", message='" + message + '\'' +
//                ", time=" + time +
                ", date=" + date +
                '}';
    }
}
