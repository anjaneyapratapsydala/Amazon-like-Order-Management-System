package com.amazonclone.order_management.dto;

import lombok.Getter;

public class LoginResponse {

    @Getter
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}