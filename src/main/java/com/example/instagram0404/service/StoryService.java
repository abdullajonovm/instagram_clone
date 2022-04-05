package com.example.instagram0404.service;

import com.example.instagram0404.entity.Attachment;
import com.example.instagram0404.entity.Story;
import com.example.instagram0404.entity.User;
import com.example.instagram0404.playload.ApiResponce;
import com.example.instagram0404.playload.PostDTO;
import com.example.instagram0404.repository.AttachmentRepository;
import com.example.instagram0404.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {
    @Autowired
    StoryRepository storyRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Story> getAllCurrentUser(User user){
        return storyRepository.findAllByUser_Id(user.getId());
    }

    public List<Story> getAllExceptCurrentUser(User user){
        return storyRepository.findAllByUser_IdNotContains(user.getId());
    }

    public ApiResponce getDetail(Integer id){
        Optional<Story> post = storyRepository.findById(id);
        if (post.isEmpty()){
            return new ApiResponce("post not found", false);
        }
        return new ApiResponce("post details", true, post.get());
    }

    public ApiResponce add(PostDTO dto, User user){
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(dto.getAttachmentId());
        Attachment attachment = attachmentOptional.get();

        Story post = new Story();
        post.setAttachment(attachment);
        post.setUser(user);
        storyRepository.save(post);
        return new ApiResponce("added", true, post);
    }

    public ApiResponce delete(Integer id, User user){

        Optional<Story> post = storyRepository.findById(id);
        if(post.isEmpty()){
            return new ApiResponce("post not found", false, true);
        }
        if (post.get().getUser()!=user){
            return new ApiResponce("you are not owner", false);
        }
        storyRepository.delete(post.get());
        return new ApiResponce("deleted", true);
    }
}
