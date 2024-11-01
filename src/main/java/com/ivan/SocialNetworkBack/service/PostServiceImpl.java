package com.ivan.SocialNetworkBack.service;

import com.cloudinary.Cloudinary;
import com.ivan.SocialNetworkBack.model.post.Post;
import com.ivan.SocialNetworkBack.model.post.PostDTO;
import com.ivan.SocialNetworkBack.model.user.User;
import com.ivan.SocialNetworkBack.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Post createPost(PostDTO postDTO, User currentUser)throws IOException  {
        Post post=new Post();
        post.setAuthorUsername(currentUser.getUsername());
        post.setDescription(postDTO.getDescription());
        post.setLikes(null);
        if (postDTO.getContentImage() != null && !postDTO.getContentImage().isEmpty()) {
            Map<String, Object> options = new HashMap<>();
            options.put("folder", "content-images");
            options.put("resource_type", "auto");

            Map uploadResult = cloudinary.uploader().upload(postDTO.getContentImage().getBytes(), options);
            post.setContentImage((String) uploadResult.get("url"));
        }
        return postRepository.save(post);
    }

    @Override
    public List<Post> getPostsByUser(String userId) {
        return List.of();
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void likePost(String postId, String userId) {

    }
}
