package com.ivan.SocialNetworkBack.model.post;

import org.springframework.web.multipart.MultipartFile;

public class PostDTO {
    private MultipartFile contentImage;
    private String description;

    public PostDTO(MultipartFile contentImage, String description) {
        this.contentImage = contentImage;
        this.description = description;
    }

    public PostDTO() {
    }

    public MultipartFile getContentImage() {
        return contentImage;
    }

    public void setContentImage(MultipartFile contentImage) {
        this.contentImage = contentImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
