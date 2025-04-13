package com.resortbooking.application.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "hotel_name")
	private String hotelName;

	@Column(name = "address")
	private String address;

	@Column(name = "description")
	private String description;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "is_available")
	private Boolean isAvailable;

	@Column(name = "website")
	private String website;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "price_perNight")
	private BigDecimal pricePerNight;

	@Column(name = "imageUrl")
	private String imageUrl;

	@OneToMany(mappedBy = "hotel")
	private List<HotelPhotos> hotelPhotos;

//	@OneToMany(mappedBy = "hotel")
//	private Set<Amenity> amenities = new HashSet<>();

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<HotelPolicy> policies = new HashSet<>();

	public Set<HotelPolicy> getPolicies() {
		return policies;
	}

	public void setPolicies(Set<HotelPolicy> policies) {
		this.policies = policies;
	}

	// Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	// ✅ Getters and Setters for amenities
//	public Set<Amenity> getAmenities() {
//		return amenities;
//	}
//
//	public void setAmenities(Set<Amenity> amenities) {
//		this.amenities = amenities;
//	}

	public List<HotelPhotos> getHotelPhotos() {
		return hotelPhotos;
	}

	public void setHotelPhotos(List<HotelPhotos> hotelPhotos) {
		this.hotelPhotos = hotelPhotos;
	}

	public BigDecimal getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(BigDecimal pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Hotel hotel = (Hotel) o;
		return id != null && id.equals(hotel.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "Hotel{" + "id=" + id + ", hotelName='" + hotelName + '\'' + ", address='" + address + '\'' + ", rating="
				+ rating + ", isAvailable=" + isAvailable + ", website='" + website + '\'' + '}';
	}
}
