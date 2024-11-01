package com.ivan.SocialNetworkBack.service;

public interface FollowService {
    void followUser(String followerId, String followingId);
    void unfollowUser(String followerId, String followingId);
}
