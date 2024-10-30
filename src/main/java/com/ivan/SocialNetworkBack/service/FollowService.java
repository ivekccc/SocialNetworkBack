package com.ivan.SocialNetworkBack.service;

import com.ivan.SocialNetworkBack.model.follow.Follow;
import com.ivan.SocialNetworkBack.model.user.User;
import com.ivan.SocialNetworkBack.repository.FollowRepository;
import com.ivan.SocialNetworkBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void followUser(String followerId, String followingId) {
        if (!followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            Follow follow = new Follow(followerId, followingId);
            follow.setCreatedAt(new Date().getTime());
            followRepository.save(follow);

            // Ažuriranje brojača
            User follower = userRepository.findById(followerId).orElseThrow();
            User following = userRepository.findById(followingId).orElseThrow();

            follower.setFollowingCount(follower.getFollowingCount() + 1);
            following.setFollowersCount(following.getFollowersCount() + 1);

            userRepository.save(follower);
            userRepository.save(following);
        }
    }

    @Transactional
    public void unfollowUser(String followerId, String followingId) {
        followRepository.deleteByFollowerIdAndFollowingId(followerId, followingId);

        // Ažuriranje brojača
        User follower = userRepository.findById(followerId).orElseThrow();
        User following = userRepository.findById(followingId).orElseThrow();

        follower.setFollowingCount(follower.getFollowingCount() - 1);
        following.setFollowersCount(following.getFollowersCount() - 1);

        userRepository.save(follower);
        userRepository.save(following);
    }

    public boolean isFollowing(String followerId, String followingId) {
        return followRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }
}