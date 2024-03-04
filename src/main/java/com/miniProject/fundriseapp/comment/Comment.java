package com.miniProject.fundriseapp.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;

    private String message;
//    private LocalTime time;
    private LocalDate date;
    @ManyToOne
    private User user;
    public Comment() {
//        this.time=LocalTime.now();
        this.date=LocalDate.now();
    }

    public Comment(Integer id, String message, LocalDate date) {
        this.id = id;
        this.message = message;
//        this.time = LocalTime.now();
        this.date = LocalDate.now();
    }

    public Comment(Integer id, String message, User user) {
        this.id = id;
        this.message = message;
//        this.time = LocalTime.now();
        this.date = LocalDate.now();
        this.user = user;

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
