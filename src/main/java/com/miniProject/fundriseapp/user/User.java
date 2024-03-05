package com.miniProject.fundriseapp.user;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.miniProject.fundriseapp.account.Account;
import com.miniProject.fundriseapp.notification.Notification;
import com.miniProject.fundriseapp.payments.Payments;
import com.miniProject.fundriseapp.post.Post;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private LocalDate dob;
    private String address;
    private String phonenumber;
    private Integer age;

    public enum Usertype {
        USER,
        INVESTOR,
        CHARITY
    }
    @Enumerated(EnumType.STRING)
    private Usertype usertype;

    private String email;
    private String password;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Account accountDetails;



    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Post> post=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Payments> payments=new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Notification> notification=new ArrayList<>();

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Integer id, String name, LocalDate dob, String address, String phonenumber, Integer age, Usertype usertype, String email, String password) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phonenumber = phonenumber;
        this.age = age;
        this.usertype = usertype;
        this.email = email;
        this.password = password;
    }

    public User(Integer id, String name, LocalDate dob, String address, String phonenumber, Integer age, Usertype usertype, Account accountDetails, List<Post> post, List<Payments> payments, List<Notification> notification) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phonenumber = phonenumber;
        this.age = age;
        this.usertype = usertype;
        this.accountDetails = accountDetails;
        this.post = post;
        this.payments = payments;
        this.notification = notification;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(Usertype usertype) {
        this.usertype = usertype;
    }

    public Account getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(Account accountDetails) {
        this.accountDetails = accountDetails;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public void setPayments(List<Payments> payments) {
        this.payments = payments;
    }

    public List<Notification> getNotification() {
        return notification;
    }
    public void setNotification(List<Notification> notification) {
        this.notification = notification;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", age=" + age +
                ", usertype=" + usertype +
                '}';
    }



}
