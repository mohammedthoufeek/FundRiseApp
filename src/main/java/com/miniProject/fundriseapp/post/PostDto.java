package com.miniProject.fundriseapp.post;


public class PostDto {

    private Integer id;

    private String title;

    private String urlField;

    private String cause;

    private String details;

    private double amountNeeded;



    public enum postType{
        Startup,
        Medical,
        CharityOrganisation;
    }
    private Post.postType postType;


    public PostDto(Integer id, String title, String urlField, String cause, String details, double amountNeeded, Post.postType postType) {
        this.id = id;
        this.title = title;
        this.urlField = urlField;
        this.cause = cause;
        this.details = details;
        this.amountNeeded = amountNeeded;
        this.postType = postType;
    }
}
