package com.ivan.SocialNetworkBack.repository;

import com.ivan.SocialNetworkBack.model.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByAuthorIdOrderByCreatedAtDesc(String authorId);
    List<Post> findAllByOrderByCreatedAtDesc();
}
