package com.resortbooking.application.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "hotel_photos")
public class HotelPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;
    
    @Column(name="caption")
    private String caption;

    @Column(name="uploadedAt")
    private LocalDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id") // Foreign key referencing Hotel
    private Hotel hotel;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

  
    // equals and hashCode

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HotelPhotos)) return false;
        HotelPhotos other = (HotelPhotos) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(imageUrl, other.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUrl);
    }

    // toString

    @Override
    public String toString() {
        return "HotelPhotos{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", hotelId=" + (hotel != null ? hotel.getId() : null) +
                '}';
    }
}
