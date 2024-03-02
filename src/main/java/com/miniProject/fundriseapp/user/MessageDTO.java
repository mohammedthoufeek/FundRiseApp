package com.miniProject.fundriseapp.user;

public class MessageDTO {
    private Integer userId;
    private Integer messageId;
    private String message;

    public MessageDTO() {
    }

    public MessageDTO(Integer userId, Integer messageId, String message) {
        this.userId = userId;
        this.messageId = messageId;
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
