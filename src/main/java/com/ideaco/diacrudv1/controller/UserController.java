package com.ideaco.diacrudv1.controller;

import com.ideaco.diacrudv1.model.UserModel;
import com.ideaco.diacrudv1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController //menandakan kalau ini controller
@RequestMapping("/api/v1") // sebagai pengantar

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome to Aufa API";
    }

    // endpoint for get all users data
    @GetMapping("/users")
    public List<UserModel> getAllUserData() {
        return userService.getAllUserData();
    }

    @GetMapping("/users/{userId}")
    public Optional<UserModel> getUserUsingId(@PathVariable("userId") int userId) {
        return userService.getUserById(userId);
    }
}
