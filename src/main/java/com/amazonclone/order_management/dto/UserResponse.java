package com.amazonclone.order_management.dto;

import lombok.Getter;

public class UserResponse{
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private String email;

    public UserResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}