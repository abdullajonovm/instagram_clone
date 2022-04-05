package com.example.instagram0404.service;


import com.example.instagram0404.entity.Attachment;
import com.example.instagram0404.entity.Post;
import com.example.instagram0404.entity.User;
import com.example.instagram0404.playload.ApiResponce;
import com.example.instagram0404.playload.PostDTO;
import com.example.instagram0404.repository.AttachmentRepository;
import com.example.instagram0404.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Post> getAllCurrentUser(User user){
        return postRepository.findAllByUser_Id(user.getId());
    }

    public List<Post> getAllExeptCurrentUser(User user){
        return postRepository.findAllByUser_IdNotContains(user.getId());
    }

    public ApiResponce getDetail(Integer id){
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()){
            return new ApiResponce("post not found", false);
        }
        return new ApiResponce("post details", true, post.get());
    }

    public ApiResponce add(PostDTO dto, User user){
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(dto.getAttachmentId());
        Attachment attachment = attachmentOptional.get();

        Post post = new Post();
        post.setAttachment(attachment);
        post.setUser(user);
        post.setDescription(dto.getDescription());
        postRepository.save(post);
        return new ApiResponce("added", true, post);
    }

    public ApiResponce delete(Integer id, User user){

        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()){
            return new ApiResponce("post not found", false, true);
        }
        if (post.get().getUser()!=user){
            return new ApiResponce("you are not owner", false);
        }
        postRepository.delete(post.get());
        return new ApiResponce("deleted", true);
    }


    public ApiResponce edit(Integer id, PostDTO postDTO, User user){
        Optional<Post> postRepo = postRepository.findById(id);
        if (postRepo.isEmpty()){
            return new ApiResponce("post not found", false);
        }
        if (postRepo.get().getUser()!=user){
            return new ApiResponce("you are not owner", false);
        }

        Post post = postRepo.get();
        post.setDescription(postDTO.getDescription());
        postRepository.save(post);
        return new ApiResponce("edited", true, post);
    }

}


