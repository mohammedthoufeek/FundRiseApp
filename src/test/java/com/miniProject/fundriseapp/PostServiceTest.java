package com.miniProject.fundriseapp;

import com.miniProject.fundriseapp.comment.CommentRepo;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostException;
import com.miniProject.fundriseapp.post.PostRepo;
import com.miniProject.fundriseapp.post.PostServiceImpl;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    private PostServiceImpl postService;
    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private UserRepo userRepo;

    @BeforeEach
    public void setup() {
        postRepo = mock(PostRepo.class);
        commentRepo = mock(CommentRepo.class);
        userRepo = mock(UserRepo.class);

        postService = new PostServiceImpl(postRepo, commentRepo, userRepo);
    }

    @Test
    public void testCreatePost_Success() throws PostException {
        User user = new User();
        Post newPost = new Post(1, "Test Title", "Test URL", "Test Cause", "Test Details", 100.0);

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(postRepo.save(any(Post.class))).thenReturn(newPost);

        Post result = postService.createPost(1, newPost);

        assertEquals(newPost, result);
        verify(userRepo, times(1)).findById(1);
        verify(postRepo, times(1)).save(any(Post.class));
        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void testGetPostById() throws PostException {
        Post existingPost = new Post(1, "Existing Post Title", "Existing URL", "Existing Cause", "Existing Details", 200.0);

        when(postRepo.findById(1)).thenReturn(Optional.of(existingPost));

        Post result = postService.getPostById(1);

        assertEquals(existingPost, result);
        verify(postRepo, times(1)).findById(1);
    }

    @Test
    public void testUpdatePost() throws PostException {
        Post existingPost = new Post(1, "Existing Post Title", "Existing URL", "Existing Cause", "Existing Details", 200.0);
        Post updatedPost = new Post(1, "Updated Post Title", "Updated URL", "Updated Cause", "Updated Details", 300.0);

        when(postRepo.findById(1)).thenReturn(Optional.of(existingPost));
        when(postRepo.save(any(Post.class))).thenReturn(updatedPost);

        Post result = postService.updatePost(updatedPost);

        assertEquals(updatedPost, result);
        verify(postRepo, times(1)).findById(1);
        verify(postRepo, times(1)).save(updatedPost);
    }

    @Test
    public void testDeletePostById() throws PostException {
        Post existingPost = new Post(1, "Existing Post Title", "Existing URL", "Existing Cause", "Existing Details", 200.0);

        when(postRepo.findById(1)).thenReturn(Optional.of(existingPost));

        Post result = postService.deletePostById(1);

        assertEquals(existingPost, result);
        verify(postRepo, times(1)).findById(1);
        verify(postRepo, times(1)).deleteById(1);
    }

    @Test
    public void testGetAllPost() throws PostException {
        List<Post> allPosts = Arrays.asList(new Post(), new Post()); // Sample list of posts

        when(postRepo.findAll()).thenReturn(allPosts);

        List<Post> result = postService.getAllPost();

        assertEquals(allPosts, result);
        verify(postRepo, times(1)).findAll();
    }

    // Add more test cases as needed

}