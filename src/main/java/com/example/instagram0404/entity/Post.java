package com.example.instagram0404.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne
    private Attachment attachment;

    @NotNull
    @ManyToOne
    private User user;

    private String description;

    private Integer likeCount;


    //    @OneToMany
//    private List<Comment> commentList;


}



