package com.miniProject.fundriseapp.user;

import jakarta.validation.constraints.NotNull;

public class MessageDTO {
    @NotNull(message = "userid cannot be null")
    private Integer userId;
    @NotNull(message = "messageid cannot be null")
    private Integer messageId;
    @NotNull(message = "message cannot be null")
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
