package com.resortbooking.application.dto;

import java.math.BigDecimal;

public class HotelDto {
    private Long id;
    private String hotelName;
    private String address;
    private String description;
    private BigDecimal pricePerNight;
    private Double rating;
    private String imageUrl;
    private Boolean isAvailable;
    private String website;
    private Double latitude;
    private Double longitude;

    // Getters and Setters
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

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }
    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    // Manually added constructor for custom use
    public HotelDto(Long id, String hotelName, String address, String description,
                    BigDecimal pricePerNight, Double rating, String imageUrl) {
        this.id = id;
        this.hotelName = hotelName;
        this.address = address;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }

    public HotelDto() {
        // Default constructor
    }
	@Override
	public String toString() {
		return "HotelDto [id=" + id + ", hotelName=" + hotelName + ", address=" + address + ", description="
				+ description + ", pricePerNight=" + pricePerNight + ", rating=" + rating + ", imageUrl=" + imageUrl
				+ ", isAvailable=" + isAvailable + ", website=" + website + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
    
}
