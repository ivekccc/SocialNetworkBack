package com.ivan.SocialNetworkBack.controller;

import com.ivan.SocialNetworkBack.model.post.Post;
import com.ivan.SocialNetworkBack.model.post.PostDTO;
import com.ivan.SocialNetworkBack.model.user.User;
import com.ivan.SocialNetworkBack.model.user.UserPrincipal;
import com.ivan.SocialNetworkBack.service.PostServiceImpl;
import com.ivan.SocialNetworkBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PostController {

    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private UserService userService;

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        User currentUser = userService.findByUsername(userPrincipal.getUsername());
        try {
            postService.createPost(postDTO, currentUser);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/allPosts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
