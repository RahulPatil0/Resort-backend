package com.resortbooking.application.dto;

public class HotelPolicyDTO {
    private Long id;
    private String policyType;
    private String description;
    private Long hotelId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public HotelPolicyDTO(Long id, String policyType, String description, Long hotelId) {
		super();
		this.id = id;
		this.policyType = policyType;
		this.description = description;
		this.hotelId = hotelId;
	}

    
}
