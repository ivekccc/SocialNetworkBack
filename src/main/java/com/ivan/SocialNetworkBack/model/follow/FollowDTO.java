package com.ivan.SocialNetworkBack.model.follow;

public class FollowDTO {
    private String followerId;
    private String followingId;

    public FollowDTO(String followerId, String followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public FollowDTO() {
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public String getFollowingId() {
        return followingId;
    }

    public void setFollowingId(String followingId) {
        this.followingId = followingId;
    }
}
