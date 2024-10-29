package com.ivan.SocialNetworkBack.controller;

import com.ivan.SocialNetworkBack.model.user.User;
import com.ivan.SocialNetworkBack.model.user.UserDTO;
import com.ivan.SocialNetworkBack.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDto){
        return userService.register(userDto);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

}
