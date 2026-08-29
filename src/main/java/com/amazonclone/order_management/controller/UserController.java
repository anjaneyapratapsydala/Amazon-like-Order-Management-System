package com.amazonclone.order_management.controller;

import com.amazonclone.order_management.dto.LoginRequest;
import com.amazonclone.order_management.dto.LoginResponse;
import com.amazonclone.order_management.dto.RegisterRequest;
import com.amazonclone.order_management.dto.UserResponse;
import com.amazonclone.order_management.entity.User;
import com.amazonclone.order_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> Register(@Valid @RequestBody RegisterRequest request){
        UserResponse response = userService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        String token = userService.login(request);

        return ResponseEntity.ok(
                new LoginResponse(token)
        );
    }
}
