package com.ideaco.diacrudv1.controller;

import com.ideaco.diacrudv1.model.UserModel;
import com.ideaco.diacrudv1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");

        List<UserModel> users = userService.getByUserName(username);

        if (!users.isEmpty()) {
            UserModel user = users.get(0);  // Assuming you want the first user in the list

            if (user.getPassword().equals(password)) {
                return "Successfully login";
            } else {
                return "Error: Invalid password";
            }
        } else {
            return "Error: User not found";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody UserModel user) {
        if (userService.isUsernameTaken(user.getUserName())) {
            return "Username is Already Taken";
        }
        userService.saveUser(user);
        return "Succesfull Register";
    }
}
