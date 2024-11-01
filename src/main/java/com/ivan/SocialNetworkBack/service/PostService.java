package com.ivan.SocialNetworkBack.service;

import com.ivan.SocialNetworkBack.model.post.Post;
import com.ivan.SocialNetworkBack.model.post.PostDTO;
import com.ivan.SocialNetworkBack.model.post.PostResponseDTO;
import com.ivan.SocialNetworkBack.model.user.User;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Post createPost(PostDTO postDTO, User currentUser) throws IOException;
    List<Post> getPostsByUser(String userId);
    List<PostResponseDTO> getAllPosts();
    void likePost(String postId, String userId);
}
