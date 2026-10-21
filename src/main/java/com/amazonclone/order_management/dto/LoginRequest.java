package com.amazonclone.order_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class LoginRequest {
    @Getter
    @Setter
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Getter
    @Setter
    @NotBlank(message = "Password is required")
    private String password;
}