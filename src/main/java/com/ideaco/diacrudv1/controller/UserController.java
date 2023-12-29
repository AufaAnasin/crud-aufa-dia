package com.ideaco.diacrudv1.controller;

import com.ideaco.diacrudv1.dto.UserNameAndResumeDTO;
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

    // bikin user, tapi hanya bisa untuk sekali input
    @PostMapping("/register")
    public String register(@RequestBody UserModel user) {
        if (userService.isUsernameTaken(user.getUserName())) {
            return "Username is Already Taken";
        }
        userService.saveUser(user);
        return "Succesfull Register";
    }

    // bikin user baru, tapi bisa banyak post
    @PostMapping("/registerMultiple")
    public List<UserModel> multipleRegister(@RequestBody List<UserModel> userModel) {
        return userService.saveMultipleUser(userModel);
    }

    // delete user

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int userId) {
        boolean response = userService.deleteUser(userId);
        if (response) {
            return "Delete Success";
        } else {
            return "Delete Failed";
        }
    }

    // edit user with put method
    @PutMapping("/edit")
    public UserModel putUser(@RequestBody UserModel userModel) {
        return userService.putUserService(userModel);
    }

    // edit user with path method
    @PatchMapping("/edit")
    public UserModel patchUser(@RequestParam("userId") int userId, @RequestParam("userResume") String userResume) {
        return userService.patchUserService(userId, userResume);
    }
    @GetMapping("/userandresumeDTO")
    public UserNameAndResumeDTO getUserAndResumeDTO(@RequestParam("userId") int userId) {
        return userService.dataUsernameResumeDTO(userId);
    }



}
