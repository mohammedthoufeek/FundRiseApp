package com.miniProject.fundriseapp.post;


import com.miniProject.fundriseapp.comment.Comment;
import com.miniProject.fundriseapp.comment.CommentException;
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

    private CommentRepo commentRepo;
    private UserRepo userRepo;

    @Override
    public String createPost(Integer userId,Post newPost) throws PostException{
//        Optional<Post> postOpt=this.postrepo.findById(newPost.getId());
//        if(postOpt.isPresent()) throw new PostException("Post is already exists");
        System.out.println("Service Working"+newPost);
        Post postObj= this.postrepo.save(newPost);

//        User userObj=this.userRepo.findById(userId).get();
//        userObj.getPost().add(postObj);
//        this.userRepo.save(userObj);
//        postObj.setUser(userObj);
        postrepo.save(postObj);
        return "Post Created Successfully";
    }

    @Override
    public Post getPostById(Integer id)throws PostException {
        Optional<Post> postOpt=this.postrepo.findById(id);
        if(postOpt.isPresent()) return this.postrepo.findById(id).get();
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
        return this.postrepo.findAll();
    }



}
