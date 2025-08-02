package com.resortbooking.application.models;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel_policies")
public class HotelPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy_type", nullable = false)
    private String policyType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, hotel, id, policyType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelPolicy other = (HotelPolicy) obj;
		return Objects.equals(description, other.description) && Objects.equals(hotel, other.hotel)
				&& Objects.equals(id, other.id) && Objects.equals(policyType, other.policyType);
	}

	@Override
	public String toString() {
		return "HotelPolicy [id=" + id + ", policyType=" + policyType + ", description=" + description + ", hotel="
				+ hotel + "]";
	}

   
    
}
