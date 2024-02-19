package com.miniProject.fundriseapp.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("post")
    public Post createPost(@RequestBody Post post )throws PostException{
        return this.postService.createPost(post);
    }

    @GetMapping("post/{id}")
    public Post getPostById(@PathVariable Integer id) throws PostException{
        return this.postService.getPostById(id);
    }

    @PutMapping("post")
    public Post updatePost(@RequestBody Post post)throws PostException{
        return this.postService.updatePost(post);
    }

    @GetMapping("posts")
    public List<Post> getPostById() throws PostException{
        return this.postService.getAllPost();
    }
    @DeleteMapping("post/{id}")
    public Post deletePostById(@PathVariable Integer id) throws PostException{
        return this.postService.deletePostById(id);
    }


}
