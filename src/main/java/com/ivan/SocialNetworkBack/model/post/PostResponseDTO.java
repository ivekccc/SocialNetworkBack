package com.ivan.SocialNetworkBack.model.post;

import java.time.LocalDate;
import java.util.List;

public class PostResponseDTO {

    private String id;
    private String contentImage;
    private String description;
    private String authorUsername;
    private String authorProfileImage;
    private LocalDate createdAt;
    private List<String> likes;

    public PostResponseDTO(String id, String contentImage, String description, String authorUsername, String authorProfileImage, LocalDate createdAt, List<String> likes) {
        this.id = id;
        this.contentImage = contentImage;
        this.description = description;
        this.authorUsername = authorUsername;
        this.authorProfileImage = authorProfileImage;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    public PostResponseDTO(String contentImage, String description, String authorUsername, String authorProfileImage, LocalDate createdAt, List<String> likes) {
        this.contentImage = contentImage;
        this.description = description;
        this.authorUsername = authorUsername;
        this.authorProfileImage = authorProfileImage;
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

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorId) {
        this.authorUsername = authorId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public String getAuthorProfileImage() {
        return authorProfileImage;
    }

    public void setAuthorProfileImage(String authorProfileImage) {
        this.authorProfileImage = authorProfileImage;
    }

    @Override
    public String toString() {
        return "PostResponseDTO{" +
                "id='" + id + '\'' +
                ", contentImage='" + contentImage + '\'' +
                ", description='" + description + '\'' +
                ", authorUsername='" + authorUsername + '\'' +
                ", authorProfileImage='" + authorProfileImage + '\'' +
                ", createdAt=" + createdAt +
                ", likes=" + likes +
                '}';
    }
}
