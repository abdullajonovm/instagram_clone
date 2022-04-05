package com.example.instagram0404.playload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String userName;
    private String phoneNumber;
    private String email;
    private Boolean gender;
    private String bio;
}
