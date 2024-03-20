package com.miniProject.fundriseapp.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class PersonalMessage {
  @Id
    @GeneratedValue
    Integer id;
    @ManyToOne
    private User user1;
    @ManyToOne
    private User user2;
    @OneToMany(mappedBy = "personalMessage",cascade = CascadeType.ALL)
    List<Message> messageList=new ArrayList<>();

    public PersonalMessage() {
    }

    public PersonalMessage(Integer id, User user1, User user2, List<Message> messageList) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.messageList = messageList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        return "PersonalMessage{" +
                "id=" + id +
                ", user1=" + user1 +
                ", user2=" + user2 +
                ", messageList=" + messageList +
                '}';
    }
}
