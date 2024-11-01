package com.ivan.SocialNetworkBack.model.post;

import com.ivan.SocialNetworkBack.model.user.UserResponseDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String contentImage;
    private String description;
    private String authorUsername;
    private LocalDate createdAt;
    private List<String> likes;

    public Post(String id, String contentImage, String description, String authorUsername, LocalDate createdAt, List<String> likes) {
        this.id = id;
        this.contentImage = contentImage;
        this.description = description;
        this.authorUsername = authorUsername;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    public Post(String contentImage, String description, String authorUsername, LocalDate createdAt, List<String> likes) {
        this.contentImage = contentImage;
        this.description = description;
        this.authorUsername = authorUsername;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    public Post() {
        this.createdAt = LocalDate.now();
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
}
