package com.miniProject.fundriseapp;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostException;
import com.miniProject.fundriseapp.post.PostServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostServiceImpl postService;

    @Test
    public void testDeletePostById() {
        int postId = 999;
        int userId = 1;


        try {
            Post deletedPost = postService.deletePostById(postId, userId);
            fail("Expected PostException was not thrown");
        } catch (PostException e) {
            assertEquals("Post Id you entered is incorrect", e.getMessage());
        }
    }

    @Test
    public void testDeletePostByIdWithIncorrectPostId() {
        int postId = 999;
        int userId = 1;

        try {
            Post deletedPost = postService.deletePostById(postId, userId);
            fail("Expected PostException was not thrown");
        } catch (PostException e) {
            assertEquals("Post Id you entered is incorrect", e.getMessage());
        }
    }


    @Test
    public void testGetAllPostWhenPostsExist() {
        Assertions.assertDoesNotThrow(() -> {
            List<Post> allPosts = postService.getAllPost();
            assertNotNull(allPosts);
            assertFalse(allPosts.isEmpty());
        });
    }





}
