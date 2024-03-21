package com.miniProject.fundriseapp;

import com.miniProject.fundriseapp.post.*;
import com.miniProject.fundriseapp.user.User;

import com.miniProject.fundriseapp.user.UserException;
import com.miniProject.fundriseapp.user.UserService;
import com.miniProject.fundriseapp.user.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    private UserService userService;



    @Test
    public void testCreatePost() throws PostCreationException {
        User user = new User(1,"Thoufeek",LocalDate.now(),"Sholinganallur","7332120965",21, User.Usertype.USER,"er@gmail.com","Erer@2121");
        Post newPost = new Post(1,"Cancer","cancer.com","Blood cancer","The patient have blood cancer",20000.0);

        // Test the createPost method
        Post createdPost = postService.createPost(user.getId(), newPost);

        assertNotNull(createdPost);
        assertEquals(newPost, createdPost); // Check if created post matches the new post
    }

//    @Test
//    public void testCreatePost_Negative()  {
//        postService=new PostServiceImpl();
//        try{
//            Assertions.assertNotNull(postService.createPost(1,new Post()));
//        }
//        catch (PostCreationException e){
//            e.printStackTrace();
//        }
//
//    }



    //+v to get post by id
//    @Test
//    void getPostById() throws PostCreationException{
//
//
//        Assertions.assertNotNull(retrievedPost);
//        Assertions.assertEquals(1, retrievedPost.getId());
//    }


    //-ve to get post by id
//    @Test
//    void getPostById_() {
//        PostException exception = Assertions.assertThrows(PostException.class, () -> {
//            postService.getPostById(1); // This should throw a PostException
//        });
//
//        Assertions.assertEquals("Post is not available for this id", exception.getMessage());
//
//    }


    //+ve to update post
//    @Test
//    void updatePost() throws PostException {
//        Post post = new Post();
//        post.setId(1);
//        post.setTitle("Updated Title");
//
//        User user = new User();
//        user.setId(1);
//
//        Post updatedPost = postService.updatePost(post, 1);
//
//        Assertions.assertNotNull(updatedPost);
//        Assertions.assertEquals("Updated Title", updatedPost.getTitle());
//    }


    //-ve to update post
//    @Test
//    void updatePost_() {
//        Post post = new Post();
//        post.setId(1);
//
//        try {
//            postService.updatePost(post, 1); // This should throw a PostException
//        } catch (PostException e) {
//            Assertions.assertEquals("Post is not available to update the post", e.getMessage());
//        }
//    }


    //+ve to delete post
//    @Test
//    void deletePostById() throws PostException {
//        Post post = new Post();
//        post.setId(1);
//
//        User user = new User();
//        user.setId(1);
//
//        Post deletedPost = postService.deletePostById(1, 1);
//
//        Assertions.assertNotNull(deletedPost);
//        Assertions.assertEquals(1, deletedPost.getId());
//    }


    //-ve to delete post
    @Test
    void deletePostById_() {
        try {
            postService.deletePostById(1, 1);
        } catch (PostException e) {
            assertEquals("Post Id you entered is incorrect", e.getMessage());
        }
    }


    //+ve test case for get all post
//    @Test
//    void getAllPost() throws PostException {
//        try {
//            List<Post> posts = new ArrayList<>();
//            PostService=new po
//
//            posts.add(post);
//
//            List<Post> retrievedPosts = postService.getAllPost();
//
//            Assertions.assertNotNull(retrievedPosts);
//            Assertions.assertEquals(1, retrievedPosts.size());
//        } catch (PostException e) {
//            Assertions.fail("Exception thrown: " + e.getMessage());
//        }
//    }



    //-ve test case for get all post
    @Test
    void getAllPost_() {
        try {
            postService.getAllPost(); // This should throw a PostException
        } catch (PostException e) {
            assertEquals("No post was created", e.getMessage());
        }
    }
}
