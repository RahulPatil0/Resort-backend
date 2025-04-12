package com.resortbooking.application.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Hotel Review.
 */
public class HotelReviewDto {

    private Long id;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
    private UserDTO user;
    private HotelDto hotel;

    // Constructors
    public HotelReviewDto() {}

    public HotelReviewDto(Long id, Integer rating, String comment, LocalDateTime createdAt,
                          UserDTO user, HotelDto hotel) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.user = user;
        this.hotel = hotel;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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
        return "HotelReviewDto{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", userId=" + (user != null ? user.getId() : null) +
                ", hotelId=" + (hotel != null ? hotel.getId() : null) +
                '}';
    }
}
