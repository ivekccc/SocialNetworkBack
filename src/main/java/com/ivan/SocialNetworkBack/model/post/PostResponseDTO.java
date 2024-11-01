package com.ivan.SocialNetworkBack.model.post;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class PostResponseDTO {

    private String id;
    private String contentImage;
    private String description;
    private String authorId;
    private ZonedDateTime createdAt;
    private List<String> likes;

    public PostResponseDTO(String id, String contentImage, String description, String authorId, ZonedDateTime createdAt, List<String> likes) {
        this.id = id;
        this.contentImage = contentImage;
        this.description = description;
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    public PostResponseDTO(String contentImage, String description, String authorId, ZonedDateTime createdAt, List<String> likes) {
        this.contentImage = contentImage;
        this.description = description;
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    public PostResponseDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentImage() {
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }
}
