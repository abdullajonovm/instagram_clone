package com.example.instagram0404.repository;

import com.example.instagram0404.entity.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpInc;

import java.util.List;
import java.util.Optional;

public interface FollowersRepository extends JpaRepository<Followers, Integer> {
//    List<Followers> findByUserId(Integer user_id);
    Optional<Followers> findByUserId(Integer user_id);

}