package com.resortbooking.application.dto;

import java.time.LocalDateTime;

/**
 * DTO for transferring user wishlist data in a clean and secure format.
 */
public class UserWishlistDTO {

    private Long id;
    private LocalDateTime createdAt;
    private UserDTO user;
    private HotelDto hotel;

    // Constructors

    public UserWishlistDTO() {
    }

    public UserWishlistDTO(Long id, LocalDateTime createdAt, UserDTO user, HotelDto hotel) {
        this.id = id;
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
        return "UserWishlistDTO{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", hotel=" + hotel +
                '}';
    }
}
