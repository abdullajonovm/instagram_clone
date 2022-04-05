package com.example.instagram0404.service;

import com.example.instagram0404.entity.User;
import com.example.instagram0404.playload.ApiResponce;
import com.example.instagram0404.playload.UserDTO;
import com.example.instagram0404.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public ApiResponce add(UserDTO dto){
        Optional<User> user = userRepository.findByUserName(dto.getUserName());
        if (!user.isEmpty()){
            return new ApiResponce("this username already exists", false);
        }
        User user1 = new User();
        user1.setUserName(dto.getUserName());
        user1.setBio(dto.getBio());
        user1.setEmail(dto.getEmail());
        user1.setGender(dto.getGender());
        user1.setPhoneNumber(dto.getPhoneNumber());
        user1.setName(dto.getName());

        return new ApiResponce("added", true, userRepository.save(user1));

    }

    public ApiResponce getDetail(Integer id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            return new ApiResponce("user not found", false);
        }
        return new ApiResponce("user details", true, user.get());
    }

    public ApiResponce edit (Integer id, UserDTO userDTO){
        Optional<User> userRepo = userRepository.findById(id);
        if (userRepo.isEmpty()){
            return new ApiResponce("user not found", false);
        }
        User user = userRepo.get();
        user.setUserName(userDTO.getUserName());
        user.setName(userDTO.getName());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBio(userDTO.getBio());
        return new ApiResponce("edited", true, user);
    }

    public ApiResponce delete(Integer id){
        Optional<User> userRepo = userRepository.findById(id);
        if (userRepo.isEmpty()){
            return new ApiResponce("user not found", false);
        }
        userRepository.delete(userRepo.get());
        return  new ApiResponce("deleted", true);
    }


}
