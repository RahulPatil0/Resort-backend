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
    private String photoUrl;
    
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

    public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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
    

 

    public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelPhotos other = (HotelPhotos) obj;
		return Objects.equals(caption, other.caption) && Objects.equals(hotel, other.hotel)
				&& Objects.equals(id, other.id) && Objects.equals(photoUrl, other.photoUrl)
				&& Objects.equals(uploadedAt, other.uploadedAt);
	}

	

    @Override
	public int hashCode() {
		return Objects.hash(caption, hotel, id, photoUrl, uploadedAt);
	}

    // toString

    @Override
    public String toString() {
        return "HotelPhotos{" +
                "id=" + id +
                ", imageUrl='" + photoUrl + '\'' +
                ", hotelId=" + (hotel != null ? hotel.getId() : null) +
                '}';
    }
}
