package com.miniProject.FundRiseApp.Service;

import com.miniProject.FundRiseApp.Entity.Post;

import java.util.List;

public interface PostService {
    List<Post> viewAllPost();
    Post viewPost(long postId);
    Post createPost(Post post);
    Post updatePost(Post post);
    Post deletePost(Post post);


}
