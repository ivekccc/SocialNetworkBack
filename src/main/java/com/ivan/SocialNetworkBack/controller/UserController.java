package com.ivan.SocialNetworkBack.controller;

import com.ivan.SocialNetworkBack.model.user.User;
import com.ivan.SocialNetworkBack.model.user.UserDTO;
import com.ivan.SocialNetworkBack.model.user.UserPrincipal;
import com.ivan.SocialNetworkBack.model.user.UserResponseDTO;
import com.ivan.SocialNetworkBack.service.UserServiceImpl;
import com.ivan.SocialNetworkBack.service.FollowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private FollowServiceImpl followServiceImpl;

    @GetMapping("/profile")
    public UserResponseDTO getUserProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = userService.findByUsername(userPrincipal.getUsername());
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getBio(),
                user.getProfileImageUrl(),
                user.getFollowersCount(),
                user.getFollowingCount()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam("name") String name,
            @RequestParam("lastname") String lastname,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage
    ) {
        try {
            UserDTO userDto = new UserDTO(name, lastname, username, password, email,profileImage);
            User registeredUser = userService.register(userDto);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

    @PostMapping("/users/{userId}/follow")
    public ResponseEntity<?> followUser(
            @PathVariable String userId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        try {
            User currentUser = userService.findByUsername(userPrincipal.getUsername());
            followServiceImpl.followUser(currentUser.getId(), userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/users/{userId}/unfollow")
    public ResponseEntity<?> unfollowUser(
            @PathVariable String userId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        try {
            User currentUser = userService.findByUsername(userPrincipal.getUsername());
            followServiceImpl.unfollowUser(currentUser.getId(), userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<UserResponseDTO>> searchUsers(@RequestParam String query) {
        List<UserResponseDTO> users = userService.searchUsers(query);
        System.out.println("111");
        return ResponseEntity.ok(users);
    }

}
