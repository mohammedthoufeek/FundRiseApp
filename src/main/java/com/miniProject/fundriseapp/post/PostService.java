package com.miniProject.fundriseapp.post;

import java.util.List;

public interface PostService {

    Post createPost(Integer userId, Post newPost) throws PostCreationException;

    Post getPostById(Integer id) throws PostException;

    Post updatePost(Post post,Integer userId) throws PostException;

    Post deletePostById(Integer id,Integer userId) throws PostException;

    List<Post> getAllPost() throws PostException ;

    List<Post> getAllPostByUserId(Integer userId) throws PostException;

    List<Post> getPostByUserId(Integer userId) throws PostException;
}
