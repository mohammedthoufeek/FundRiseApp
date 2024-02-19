package com.miniProject.FundRiseApp.User;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Message {
    @Id
    //@GeneratedValue
    Integer id;
    private LocalDate date;
    private LocalDate time;
    private String message;
    @ManyToOne
    @JoinColumn(name = "messages")
    private PersonalMessage personalMessage;

    public Message(Integer id, LocalDate date, LocalDate time, String message, PersonalMessage personalMessage) {
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

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
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
