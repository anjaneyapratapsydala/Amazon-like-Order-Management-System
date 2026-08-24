package com.amazonclone.order_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class RegisterRequest {
    @Setter
    @Getter
    @NotBlank(message = "Name is Required")
    private String name;

    @Getter
    @Setter
    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid email format")
    private  String email;

    @Getter
    @Setter
    @NotBlank
    @Size(min=8,message="password must contain atleast 8 characters")
    private String password;

    public RegisterRequest() {
    }

}