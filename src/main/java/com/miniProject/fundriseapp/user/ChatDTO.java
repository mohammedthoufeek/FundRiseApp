package com.miniProject.fundriseapp.user;

public class ChatDTO {
    private Integer userid1;
    private Integer userid2;
    private String message;
    public ChatDTO() {
    }
    public ChatDTO(Integer id1, Integer id2, String message) {
        this.userid1 = id1;
        this.userid2 = id2;
        this.message = message;
    }


    public Integer getUserid1() {
        return userid1;
    }

    public void setUserid1(Integer userid1) {
        this.userid1 = userid1;
    }

    public Integer getUserid2() {
        return userid2;
    }

    public void setUserid2(Integer userid2) {
        this.userid2 = userid2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
