package com.microservices.user_service.controller;

import com.microservices.user_service.common.ApiResponse;
import com.microservices.user_service.dto.UserRequest;
import com.microservices.user_service.entity.User;
import com.microservices.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody UserRequest request){
        User user = userService.createUser(request);
        return ApiResponse.<User>builder()
                .success(true)
                .message("User created successfully")
                .data(user)
                .build();
    }

    @GetMapping
    public ApiResponse<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return ApiResponse.<List<User>>builder()
                .success(true)
                .message("Users fetched")
                .data(users)
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<User> getUserById(@PathVariable Long userId){
        User user = userService.getUserById(userId);
        return ApiResponse.<User>builder()
                .success(true)
                .message("Users fetched")
                .data(user)
                .build();
    }
}
