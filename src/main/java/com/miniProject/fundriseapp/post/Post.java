package com.miniProject.fundriseapp.post;


import com.miniProject.fundriseapp.comment.Comment;
import com.miniProject.fundriseapp.payments.Payments;
import com.miniProject.fundriseapp.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {
    @Id
    //@GeneratedValue
    private Integer id;
    private String post;
    private String urlField;
    private String cause;
    private String details;
    private double amountNeeded;

    public enum postType{
        Startup,
        Medical,
        CharityOrganisation
    }
    @Enumerated(EnumType.STRING)
    private Post.postType usertype;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comment=new ArrayList<>();
    private double amountreceived;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Payments> payments=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
    }

    public Post(Integer id, String post, String urlField, String cause, String details, double amountNeeded) {
        this.id = id;
        this.post = post;
        this.urlField = urlField;
        this.cause = cause;
        this.details = details;
        this.amountNeeded = amountNeeded;
    }

    public Post(Integer id, String post, String urlField, String cause, String details, double amountNeeded, postType usertype, List<Comment> comment, double amountreceived, List<Payments> payments, User user) {
        this.id = id;
        this.post = post;
        this.urlField = urlField;
        this.cause = cause;
        this.details = details;
        this.amountNeeded = amountNeeded;
        this.usertype = usertype;
        this.comment = comment;
        this.amountreceived = amountreceived;
        this.payments = payments;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getUrlField() {
        return urlField;
    }

    public void setUrlField(String urlField) {
        this.urlField = urlField;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getAmountNeeded() {
        return amountNeeded;
    }

    public void setAmountNeeded(double amountNeeded) {
        this.amountNeeded = amountNeeded;
    }

    public postType getUsertype() {
        return usertype;
    }

    public void setUsertype(postType usertype) {
        this.usertype = usertype;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public double getAmountreceived() {
        return amountreceived;
    }

    public void setAmountreceived(double amountreceived) {
        this.amountreceived = amountreceived;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public void setPayments(List<Payments> payments) {
        this.payments = payments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", urlField='" + urlField + '\'' +
                ", cause='" + cause + '\'' +
                ", details='" + details + '\'' +
                ", amountNeeded=" + amountNeeded +
                '}';
    }
}
