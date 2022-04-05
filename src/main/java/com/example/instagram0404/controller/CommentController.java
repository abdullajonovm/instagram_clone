package com.example.instagram0404.controller;

import com.example.instagram0404.entity.Comment;
import com.example.instagram0404.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    final CommentService commentService;

    @GetMapping("/{id}")
    public HttpEntity<?> getCommit(@PathVariable Integer postId){
        List<Comment> commet = commentService.getCommet(postId);
        return ResponseEntity.ok().body(commet);
    }

    @PostMapping("/{id}")
    public HttpEntity<?> addComment(@PathVariable Integer postId, @RequestParam Integer userId, @RequestParam String message){
        List<Comment> commentList = commentService.addComment(postId, userId, message);
        return ResponseEntity.ok().body(commentList);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletComment(@PathVariable Integer postId, @RequestParam Integer commentId){
        List<Comment> commentList = commentService.delete(commentId, postId);
        return ResponseEntity.ok().body(commentList);
    }
}
