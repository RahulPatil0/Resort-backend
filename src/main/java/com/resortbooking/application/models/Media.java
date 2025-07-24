package com.resortbooking.application.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import com.resortbooking.application.utils.Enums;

@Entity
@Table(name = "hotel_photos")
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "media_type")
	private Enums.MediaType mediaType;

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "caption")
	private String caption;

	@Column(name = "uploadedAt")
	private LocalDateTime uploadedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_id") // Foreign key referencing Hotel
	private Hotel hotel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Rooms rooms;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhotoUrl() {
		return url;
	}

	public void setPhotoUrl(String photoUrl) {
		this.url = photoUrl;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Rooms getRooms() {
		return rooms;
	}

	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Media other = (Media) obj;
		return Objects.equals(caption, other.caption) && Objects.equals(hotel, other.hotel)
				&& Objects.equals(id, other.id) && Objects.equals(url, other.url)
				&& Objects.equals(uploadedAt, other.uploadedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(caption, hotel, id, url, uploadedAt);
	}

	// toString
	@Override
	public String toString() {
		return "HotelPhotos{" + "id=" + id + ", imageUrl='" + url + '\'' + ", hotelId="
				+ (hotel != null ? hotel.getId() : null) + '}';
	}
}
