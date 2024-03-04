package com.miniProject.fundriseapp.post;


import com.miniProject.fundriseapp.comment.CommentRepo;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

@Autowired
    private PostRepo postrepo;
@Autowired
    private CommentRepo commentRepo;
@Autowired
    private UserRepo userRepo;

    public PostServiceImpl(PostRepo postRepo, CommentRepo commentRepo, UserRepo userRepo) {
        this.postrepo = postRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Post createPost(Integer userId, Post newPost) throws PostException{

        System.out.println("Service Working" + newPost);
        User userObj = this.userRepo.findById(userId).orElse(null);
        if (userObj != null) {
            // Set the user for the new post
            newPost.setUser(userObj);
            // Save the post
            Post postObj = this.postrepo.save(newPost);
            // Add the post to the user's list of posts
            userObj.getPost().add(postObj);
            // Save the user object
            this.userRepo.save(userObj);
            return postObj;
        } else {
            throw new PostException("User not found");
        }
    }

    @Override
    public Post getPostById(Integer id)throws PostException {
        Optional<Post> postOpt=this.postrepo.findById(id);
        if(postOpt.isPresent()) {
            System.out.println("getpost"+ postOpt);
            return postOpt.get();
        }
        else throw new PostException("No post was created yet!!!");
    }

    @Override
    public Post updatePost(Post post)throws PostException {
        Optional<Post> postOpt =this.postrepo.findById(post.getId());
        if(postOpt.isPresent()) return this.postrepo.save(post);
        else throw new PostException("Post not found to update");
    }

    @Override
    public Post deletePostById(Integer id)throws PostException {
        Optional<Post> postOpt=this.postrepo.findById(id);
        if (postOpt.isPresent()) {
            this.postrepo.deleteById(id);
            return postOpt.get();
        }
        else throw new PostException("Post Id you entered is incorrect");
    }

    @Override
    public List<Post> getAllPost()throws PostException {
        List<Post> postOpt=this.postrepo.findAll();
        if(postOpt.isEmpty()) throw new PostException("No post was created");
        return postOpt;
    }



}
