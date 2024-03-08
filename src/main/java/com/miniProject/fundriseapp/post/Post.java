package com.miniProject.fundriseapp.post;


import com.fasterxml.jackson.annotation.*;
import com.miniProject.fundriseapp.comment.Comment;
import com.miniProject.fundriseapp.transactions.Transaction;
import com.miniProject.fundriseapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
//@Table(name = "Post",schema = "public")
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank(message = "title Field shouldnot be blank")
    private String title;
    @NotBlank(message = "UrlField shouldnot be blank")
    private String urlField;
    @NotBlank(message = "Message shouldnot be blank")
    private String cause;
    @NotBlank(message = "Details shouldnot be blank")
    private String details;
    @NotNull(message = "amount shouldnot be blank")
    private double amountNeeded;

    public Post() {

    }

    public enum postType{
        STARTUP,
        MEDICAL,
        CHARITYORGANIZATION
    }
    @Enumerated(EnumType.STRING)
    private Post.postType postType;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comment=new ArrayList<>();
    @NotNull(message = "amount shouldnot be blank")
    private double amountreceived;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Transaction> payments=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Post(Integer id, String title, String urlField, String cause, String details, double amountNeeded) {
        this.id = id;
        this.title = title;
        this.urlField = urlField;
        this.cause = cause;
        this.details = details;
        this.amountNeeded = amountNeeded;
    }

    public Post(Integer id, String title, String urlField, String cause, String details, double amountNeeded, postType usertype, List<Comment> comment, double amountreceived, List<Transaction> payments, User user) {
        this.id = id;
        this.title = title;
        this.urlField = urlField;
        this.cause = cause;
        this.details = details;
        this.amountNeeded = amountNeeded;
        this.postType = usertype;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String post) {
        this.title = post;
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

    public postType getPostType() {
        return postType;
    }

    public void setPostType(postType usertype) {
        this.postType = usertype;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public double getAmountReceived() {
        return amountreceived;
    }

    public void setAmountReceived(double amountReceived) {
        this.amountreceived = amountReceived;
    }

    public List<Transaction> getPayments() {
        return payments;
    }

    public void setPayments(List<Transaction> payments) {
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
                ", post='" + title + '\'' +
                ", urlField='" + urlField + '\'' +
                ", cause='" + cause + '\'' +
                ", details='" + details + '\'' +
                ", amountNeeded=" + amountNeeded +
                '}';
    }
}
