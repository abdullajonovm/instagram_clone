package com.example.instagram0404.controller;

import com.example.instagram0404.aop.CurrentUser;
import com.example.instagram0404.entity.Story;
import com.example.instagram0404.entity.User;
import com.example.instagram0404.playload.PostDTO;
import com.example.instagram0404.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
@RequiredArgsConstructor
public class StoryController {
    private final StoryService storyService;

    @GetMapping("/allUser")
    public HttpEntity<?> getAllUserPost(@CurrentUser User user){
        List<Story> all = storyService.getAllCurrentUser(user);
        return ResponseEntity.ok().body(all);

    }

    @GetMapping("/allNotUser")
    public HttpEntity<?> getAllNotUserPost(@CurrentUser User user){
        List<Story> all = storyService.getAllExceptCurrentUser(user);
        return ResponseEntity.ok().body(all);

    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody PostDTO postDTO, @CurrentUser User user){
        return ResponseEntity.ok().body(storyService.add(postDTO, user));

    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id, @CurrentUser User user){
        return ResponseEntity.ok().body(storyService.delete(id, user));
    }

    @GetMapping("/{id}")
    public  HttpEntity<?> postDetail(@PathVariable Integer id){
        return ResponseEntity.ok().body(storyService.getDetail(id));
    }
}