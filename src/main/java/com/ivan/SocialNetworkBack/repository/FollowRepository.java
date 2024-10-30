package com.ivan.SocialNetworkBack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ivan.SocialNetworkBack.model.follow.Follow;

@Repository
public interface FollowRepository extends MongoRepository<Follow, String> {
    boolean existsByFollowerIdAndFollowingId(String followerId, String followingId);
    void deleteByFollowerIdAndFollowingId(String followerId, String followingId);
    int countByFollowerId(String followerId);
    int countByFollowingId(String followingId);
}