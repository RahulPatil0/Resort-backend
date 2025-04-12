package com.resortbooking.application.dto;

public class HotelPolicyDTO {
    private Long id;
    private String policyType;
    private String description;
    private Long hotelId;

    // Constructors
    public HotelPolicyDTO() {
    }

    public HotelPolicyDTO(Long id, String policyType, String description, Long hotelId) {
        this.id = id;
        this.policyType = policyType;
        this.description = description;
        this.hotelId = hotelId;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getPolicyType() {
        return policyType;
    }

    public String getDescription() {
        return description;
    }

    public Long getHotelId() {
        return hotelId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}
