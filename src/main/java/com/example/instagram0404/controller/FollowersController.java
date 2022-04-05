package com.example.instagram0404.controller;

import com.example.instagram0404.dto.ApiResponse;
import com.example.instagram0404.entity.User;
import com.example.instagram0404.service.FollowersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/followers")
@RequiredArgsConstructor
public class FollowersController {
    final FollowersService followersService;

    @GetMapping("/{id}")
    public HttpEntity<?> findAllFollowers(@PathVariable Integer id, User user){
        ApiResponse apiResponse = followersService.findAllFollowers(id, user);
        return ResponseEntity.ok().body(apiResponse);
    }

}
