package com.example.instagram0404.playload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponce {
    private String message;
    private Boolean success;
    private Object object;

    public ApiResponce(String message, Boolean success){
        this.message=message;
        this.success=success;
    }
}
