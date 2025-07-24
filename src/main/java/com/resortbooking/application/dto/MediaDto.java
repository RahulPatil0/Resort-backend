package com.resortbooking.application.dto;

import java.time.LocalDateTime;

import com.resortbooking.application.utils.Enums;

/**
 * Data Transfer Object for Hotel Photos.
 */
public class MediaDto {

    private Long id;
    private Enums.MediaType mediaType;
    private String url;
    private LocalDateTime uploadedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

	public Enums.MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(Enums.MediaType mediaType) {
		this.mediaType = mediaType;
	}
}
