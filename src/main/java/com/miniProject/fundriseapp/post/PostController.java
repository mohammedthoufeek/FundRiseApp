package com.miniProject.fundriseapp.post;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("post")


    public Post createPost(@Valid @RequestBody Post post, Integer user_id)throws PostCreationException{
        return this.postService.createPost(user_id,post);

    }

    @GetMapping("post/{id}")
    public Post getPostById(@PathVariable Integer id) throws PostException{
        return this.postService.getPostById(id);
    }


    @PutMapping("post")
    public Post updatePost(@Valid @RequestBody Post post,Integer userId)throws PostException{
        return this.postService.updatePost(post,userId);
    }

    @GetMapping("posts")
    public List<Post> getPostById() throws PostException{
        return this.postService.getAllPost();
    }
    @DeleteMapping("post/{id}")
    public Post deletePostById(@PathVariable Integer id,Integer userId) throws PostException{
        return this.postService.deletePostById(id,userId);
    }


}
