package com.miniProject.fundriseapp.user;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.miniProject.fundriseapp.bankAccount.BankAccount;
import com.miniProject.fundriseapp.notification.Notification;
import com.miniProject.fundriseapp.transactions.Transaction;
import com.miniProject.fundriseapp.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

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
    @NotBlank(message = "Please provide a username")
    private String name;
    @NotNull(message = "Date of birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @NotBlank(message = "Should not be null")
    private String address;
    @Pattern(regexp = "[0-9]{3}[0-9]{3}[0-9]{4}", message = "Invalid phone number format")
    private String phonenumber;
    @NotNull(message = "age should not  be null")
    private Integer age;

    public enum Usertype {
        USER,
        INVESTOR,
        CHARITY
    }
    @Enumerated(EnumType.STRING)
    private Usertype usertype;
    @Email(message = "Invalid email format")
    private String email;
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", message = "Password must contain at least one digit, one lowercase and one uppercase letter, and be 6-12 characters long")
    private String password;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private BankAccount bankAccountDetails;



    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Post> post=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Transaction> payments=new ArrayList<>();
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

    public User(Integer id, String name, LocalDate dob, String address, String phonenumber, Integer age, Usertype usertype, BankAccount bankAccountDetails, List<Post> post, List<Transaction> payments, List<Notification> notification) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phonenumber = phonenumber;
        this.age = age;
        this.usertype = usertype;
        this.bankAccountDetails = bankAccountDetails;
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

    public BankAccount getAccountDetails() {
        return bankAccountDetails;
    }

    public void setAccountDetails(BankAccount bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public List<Transaction> getPayments() {
        return payments;
    }

    public void setPayments(List<Transaction> payments) {
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
