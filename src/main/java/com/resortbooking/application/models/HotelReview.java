package com.resortbooking.application.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "hotel_reviews")
public class HotelReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer rating; // 1 to 5 stars

	@Column(length = 1000)
	private String comment;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// equals and hashCode

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof HotelReview))
			return false;
		HotelReview that = (HotelReview) o;
		return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(hotel, that.hotel);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, hotel);
	}

	// toString

	@Override
	public String toString() {
		return "HotelReview{" + "id=" + id + ", rating=" + rating + ", comment='" + comment + '\'' + ", hotelId="
				+ (hotel != null ? hotel.getId() : null) + ", userId=" + (user != null ? user.getId() : null) + '}';
	}
}
