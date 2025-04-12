package com.resortbooking.application.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Hotel Photos.
 */
public class HotelPhotosDto {

    private Long id;
    private String photoUrl;
    private LocalDateTime uploadedAt;
    private HotelDto hotel;

    // Constructors
    public HotelPhotosDto() {}

    public HotelPhotosDto(Long id, String photoUrl, LocalDateTime uploadedAt, HotelDto hotel) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.uploadedAt = uploadedAt;
        this.hotel = hotel;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public HotelDto getHotel() {
        return hotel;
    }

    public void setHotel(HotelDto hotel) {
        this.hotel = hotel;
    }

    // toString

    @Override
    public String toString() {
        return "HotelPhotosDto{" +
                "id=" + id +
                ", photoUrl='" + photoUrl + '\'' +
                ", uploadedAt=" + uploadedAt +
                ", hotelId=" + (hotel != null ? hotel.getId() : null) +
                '}';
    }
}
