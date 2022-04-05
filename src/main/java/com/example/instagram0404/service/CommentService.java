package com.example.instagram0404.service;

import com.example.instagram0404.entity.Comment;
import com.example.instagram0404.entity.Post;
import com.example.instagram0404.entity.User;
import com.example.instagram0404.repository.CommentRepository;
import com.example.instagram0404.repository.PostRepository;
import com.example.instagram0404.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    final CommentRepository commentRepository;
    final UserRepository userRepository;
    final PostRepository postRepository;

    public List<Comment> getCommet(Integer postId) {
        List<Comment> byPostId = commentRepository.findByPostId(postId);
        return byPostId;
    }

    public List<Comment> addComment(Integer postId, Integer userId, String message) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        User user = userOptional.get();
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setMessage(message);
        comment.setPost(post);
        commentRepository.save(comment);

        List<Comment> byPostId = commentRepository.findByPostId(postId);
        return byPostId;
    }

    public List<Comment> delete(Integer commentId, Integer postId) {
        commentRepository.deleteById(commentId);
        return commentRepository.findByPostId(postId);
    }
}
