package com.example.instagram0404.service;

import com.example.instagram0404.dto.ApiResponse;
import com.example.instagram0404.entity.Followers;
import com.example.instagram0404.entity.User;
import com.example.instagram0404.repository.FollowersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowersService {

    final FollowersRepository followersRepository;

    public ApiResponse findAllFollowers(Integer id, User user) {
        Optional<Followers> followersList = followersRepository.findByUserId(user.getId());
        return new ApiResponse("This followers", true, followersList);
    }
}
