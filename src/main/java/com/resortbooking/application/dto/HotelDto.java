//package com.resortbooking.application.dto;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Set;
//
///**
// * DTO for transferring Hotel data between layers.
// */
//public class HotelDto {
//
//    private Long id;
//    private String hotelName;
//    private String address;
//    private String description;
//    private String contactEmail;
//    private String contactNumber;
//    private BigDecimal pricePerNight;
//    private Double rating;
//    private Boolean isAvailable;
//    private String website;
//    private LocalDateTime createdAt;
//    private LocalDateTime lastUpdatedAt;
//    private Double latitude;
//    private Double longitude;
//	private LocalDateTime checkInTime;
//	private LocalDateTime checkOutTime;
//	private String ownerEmail;
//
//    private List<MediaDto> media;
//    private List<String> amenity;
//    private Set<HotelPolicyDTO> policyDetails;
//    private List<DocumentsDTO> documents;
//
//    // Getters & Setters
//    public Set<HotelPolicyDTO> getPolicyDetails() {
//		return policyDetails;
//	}
//
//	public List<DocumentsDTO> getDocuments() {
//		return documents;
//	}
//
//	public void setDocuments(List<DocumentsDTO> documents) {
//		this.documents = documents;
//	}
//
//	public void setPolicyDetails(Set<HotelPolicyDTO> policyDetails) {
//		this.policyDetails = policyDetails;
//	}
//
//	public List<String> getAmenity() {
//		return amenity;
//	}
//
//	public void setAmenity(List<String> amenity) {
//		this.amenity = amenity;
//	}
//
//	public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getHotelName() {
//        return hotelName;
//    }
//
//    public void setHotelName(String hotelName) {
//        this.hotelName = hotelName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getContactEmail() {
//        return contactEmail;
//    }
//
//    public void setContactEmail(String contactEmail) {
//        this.contactEmail = contactEmail;
//    }
//
//    public BigDecimal getPricePerNight() {
//        return pricePerNight;
//    }
//
//    public void setPricePerNight(BigDecimal pricePerNight) {
//        this.pricePerNight = pricePerNight;
//    }
//
//    public Double getRating() {
//        return rating;
//    }
//
//    public void setRating(Double rating) {
//        this.rating = rating;
//    }
//
//    public Boolean getIsAvailable() {
//        return isAvailable;
//    }
//
//    public void setIsAvailable(Boolean isAvailable) {
//        this.isAvailable = isAvailable;
//    }
//
//    public String getWebsite() {
//        return website;
//    }
//
//    public void setWebsite(String website) {
//        this.website = website;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getLastUpdatedAt() {
//        return lastUpdatedAt;
//    }
//
//    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
//        this.lastUpdatedAt = lastUpdatedAt;
//    }
//
//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(Double latitude) {
//        this.latitude = latitude;
//    }
//
//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(Double longitude) {
//        this.longitude = longitude;
//    }
//
//	public List<MediaDto> getMedia() {
//		return media;
//	}
//
//	public void setMedia(List<MediaDto> media) {
//		this.media = media;
//	}
//
//	public LocalDateTime getCheckInTime() {
//		return checkInTime;
//	}
//
//	public void setCheckInTime(LocalDateTime checkInTime) {
//		this.checkInTime = checkInTime;
//	}
//
//	public LocalDateTime getCheckOutTime() {
//		return checkOutTime;
//	}
//
//	public void setCheckOutTime(LocalDateTime checkOutTime) {
//		this.checkOutTime = checkOutTime;
//	}
//
//	public String getContactNumber() {
//		return contactNumber;
//	}
//
//	public void setContactNumber(String contactNumber) {
//		this.contactNumber = contactNumber;
//	}
//	public String getOwnerEmail() {
//	    return ownerEmail;
//	}
//
//	public void setOwnerEmail(String ownerEmail) {
//	    this.ownerEmail = ownerEmail;
//	}
//	
//}
package com.resortbooking.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HotelDto {

    private Long id;
    private String hotelName = "";
    private String address = "";
    private String description = "";
    private String contactEmail = "";
    private String contactNumber = "";
    private BigDecimal pricePerNight;
    private BigDecimal minimumPrice;
    private Double rating;
    private Boolean isAvailable;
    private String website = "";
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private Double latitude;
    private Double longitude;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String ownerEmail = "";

    private List<MediaDto> media = new ArrayList<>();
    private List<String> amenity = new ArrayList<>();

    // Updated to match entity naming
    private Set<HotelPolicyDTO> policies = new HashSet<>();

    private List<DocumentsDTO> documents = new ArrayList<>();

    // ---- Getters & Setters ----

    public Set<HotelPolicyDTO> getPolicies() {
        return policies;
    }

    public void setPolicies(Set<HotelPolicyDTO> policies) {
        this.policies = policies;
    }

    public List<DocumentsDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentsDTO> documents) {
        this.documents = documents;
    }

    public List<String> getAmenity() {
        return amenity;
    }

    public void setAmenity(List<String> amenity) {
        this.amenity = amenity;
    }

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
        this.hotelName = hotelName != null ? hotelName : "";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address != null ? address : "";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail != null ? contactEmail : "";
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
        this.website = website != null ? website : "";
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

    public List<MediaDto> getMedia() {
        return media;
    }

    public void setMedia(List<MediaDto> media) {
        this.media = media;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber != null ? contactNumber : "";
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail != null ? ownerEmail : "";
    }

	public BigDecimal getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(BigDecimal minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
    
}
