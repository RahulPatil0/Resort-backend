package com.resortbooking.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for transferring Hotel data between layers.
 */
public class HotelDto {

    private Long id;
    private String hotelName;
    private String address;
    private String description;
    private String contactEmail;
    private BigDecimal pricePerNight;
    private Double rating;
    private Boolean isAvailable;
    private String website;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private Double latitude;
    private Double longitude;

    private List<HotelPhotosDto> hotelPhotos;

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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
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

    public List<HotelPhotosDto> getHotelPhotos() {
        return hotelPhotos;
    }

    public void setHotelPhotos(List<HotelPhotosDto> hotelPhotos) {
        this.hotelPhotos = hotelPhotos;
    }

    @Override
    public String toString() {
        return "HotelDto [id=" + id + ", hotelName=" + hotelName + ", address=" + address + ", description="
                + description + ", contactEmail=" + contactEmail + ", pricePerNight=" + pricePerNight + ", rating="
                + rating + ", isAvailable=" + isAvailable + ", website=" + website + ", createdAt=" + createdAt
                + ", lastUpdatedAt=" + lastUpdatedAt + ", latitude=" + latitude + ", longitude=" + longitude
                + ", hotelPhotos=" + hotelPhotos + "]";
    }
}
