package com.miniProject.fundriseapp.post;

import java.util.List;

public interface PostService {

    String createPost(Integer userId,Post newPost) throws PostException;

    Post getPostById(Integer id) throws PostException;

    Post updatePost(Post post) throws PostException;

    Post deletePostById(Integer id) throws PostException;

    List<Post> getAllPost() throws PostException ;

}
