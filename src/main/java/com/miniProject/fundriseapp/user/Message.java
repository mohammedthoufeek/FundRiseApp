package com.miniProject.fundriseapp.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class Message {
    @Id
    @GeneratedValue
    Integer id;
    @NotNull(message = "Date of birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private LocalTime time;
    @NotBlank(message = "Message should not be null")
    private String message;
    @ManyToOne
    @JoinColumn(name = "messages")
    private PersonalMessage personalMessage;
    @ManyToOne
    private User user;

    public Message(Integer id, LocalDate date, LocalTime time, String message, PersonalMessage personalMessage, User user) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.message = message;
        this.personalMessage = personalMessage;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message(Integer id, LocalDate date, LocalTime time, String message, PersonalMessage personalMessage) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.message = message;
        this.personalMessage = personalMessage;
    }

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonalMessage getPersonalMessage() {
        return personalMessage;
    }

    public void setPersonalMessage(PersonalMessage personalMessage) {
        this.personalMessage = personalMessage;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", message='" + message + '\'' +
                ", personalMessage=" + personalMessage +
                '}';
    }
}
