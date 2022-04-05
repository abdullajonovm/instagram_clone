package com.example.instagram0404.repository;

import com.example.instagram0404.entity.Post;
import com.example.instagram0404.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    List<Story> findAllByUser_Id(Integer id);
//    @Query(value = "select p from STORY p where users_id != id", nativeQuery = true)
List<Story> findAllByUser_IdNotContains(Integer id);;
}
