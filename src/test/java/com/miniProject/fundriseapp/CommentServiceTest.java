package com.miniProject.fundriseapp;

import com.miniProject.fundriseapp.comment.*;
import com.miniProject.fundriseapp.post.Post;
import com.miniProject.fundriseapp.post.PostRepo;
import com.miniProject.fundriseapp.user.User;
import com.miniProject.fundriseapp.user.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    private CommentServiceImpl commentService;
    private CommentRepo commentRepo;
    private PostRepo postRepo;
    private UserRepo userRepo;

    @BeforeEach
    public void setup() {
        commentRepo = mock(CommentRepo.class);
        postRepo = mock(PostRepo.class);
        userRepo = mock(UserRepo.class);

        commentService = new CommentServiceImpl(commentRepo, postRepo, userRepo);
    }

    @Test
    public void testCreateComment_Success() throws CommentException {
        CommentDto validCommentDto = new CommentDto(1, 1, "Test message", LocalDate.now());
        Post post = new Post();
        User user = new User();

        when(postRepo.findById(1)).thenReturn(Optional.of(post));
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(commentRepo.save(any(Comment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String result = commentService.createComment(validCommentDto);

        assertEquals("Comment Created Successfully", result);
        verify(postRepo, times(1)).findById(1);
        verify(userRepo, times(1)).findById(1);
        verify(commentRepo, times(1)).save(any(Comment.class));
        verify(postRepo, times(1)).save(post);
    }

    @Test
    public void testUpdateComment() throws CommentException {
        Comment existingComment = new Comment(1, "Existing Comment Message",LocalDate.now());
        Comment updatedComment = new Comment(1, "Updated Comment Message", LocalDate.now());

        when(commentRepo.findById(1)).thenReturn(Optional.of(existingComment));
        when(commentRepo.save(any(Comment.class))).thenReturn(updatedComment);

        Comment result = commentService.updateComment(updatedComment);

        assertEquals(updatedComment, result);
        verify(commentRepo, times(1)).findById(1);
        verify(commentRepo, times(1)).save(updatedComment);
    }

    @Test
    public void testDeleteCommentById() throws CommentException {
        Comment existingComment = new Comment(1, "Existing Comment Message", LocalDate.now());

        when(commentRepo.findById(1)).thenReturn(Optional.of(existingComment));

        Comment result = commentService.deleteCommentById(1);

        assertEquals(existingComment, result);
        verify(commentRepo, times(1)).findById(1);
        verify(commentRepo, times(1)).deleteById(1);
    }

    @Test
    public void testGetAllComments() throws CommentException {
        List<Comment> allComments = Arrays.asList(new Comment(), new Comment()); // Sample list of comments

        when(commentRepo.findAll()).thenReturn(allComments);

        List<Comment> result = commentService.getAllComments();

        assertEquals(allComments, result);
        verify(commentRepo, times(1)).findAll();
    }

    @Test
    public void testUpdateMessage() throws CommentException {
        Comment existingComment = new Comment(1, "Existing Comment Message", LocalDate.now());
        String updatedMessage = "Updated Message";

        when(commentRepo.findById(1)).thenReturn(Optional.of(existingComment));
        when(commentRepo.save(any(Comment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Comment result = commentService.updateMessage(1, updatedMessage);

        assertEquals(updatedMessage, result.getMessage());
        verify(commentRepo, times(1)).findById(1);
        verify(commentRepo, times(1)).save(existingComment);
    }
    @Test
    public void testGetCommentById() throws CommentException {
        Comment existingComment = new Comment(1, "Existing Comment Message", LocalDate.now());

        when(commentRepo.findById(1)).thenReturn(Optional.of(existingComment));

        Comment result = commentService.getCommentById(1);

        assertEquals(existingComment, result);
        verify(commentRepo, times(1)).findById(1);
    }
}