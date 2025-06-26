package com.resortbooking.application.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_license")
    private String registrationLicense;

    @Column(name = "owner_aadhaar_card")
    private String ownerAadhaarCard;

    @Column(name = "owner_pan_card")
    private String ownerPanCard;

    @Column(name = "hotel_account_details")
    private String hotelAccountDetails;

    @Column(name = "cancelled_cheque")
    private String cancelledCheque;

    @Column(name = "fssai_license_restaurant")
    private String fssaiLicenseRestaurant;

    @Column(name = "pool_registration_certificate")
    private String poolRegistrationCertificate;

    @Column(name = "wildlife_view")
    private Boolean wildlifeView;

    @Column(name = "eco_friendly_certification")
    private Boolean ecoFriendlyCertification;

    @Column(name = "river_view")
    private Boolean riverView;

    @Column(name = "resort_type")
    private String resortType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;
    
    @OneToOne 
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationLicense() {
        return registrationLicense;
    }

    public void setRegistrationLicense(String registrationLicense) {
        this.registrationLicense = registrationLicense;
    }

    public String getOwnerAadhaarCard() {
        return ownerAadhaarCard;
    }

    public void setOwnerAadhaarCard(String ownerAadhaarCard) {
        this.ownerAadhaarCard = ownerAadhaarCard;
    }

    public String getOwnerPanCard() {
        return ownerPanCard;
    }

    public void setOwnerPanCard(String ownerPanCard) {
        this.ownerPanCard = ownerPanCard;
    }

    public String getHotelAccountDetails() {
        return hotelAccountDetails;
    }

    public void setHotelAccountDetails(String hotelAccountDetails) {
        this.hotelAccountDetails = hotelAccountDetails;
    }

    public String getCancelledCheque() {
        return cancelledCheque;
    }

    public void setCancelledCheque(String cancelledCheque) {
        this.cancelledCheque = cancelledCheque;
    }

    public String getFssaiLicenseRestaurant() {
        return fssaiLicenseRestaurant;
    }

    public void setFssaiLicenseRestaurant(String fssaiLicenseRestaurant) {
        this.fssaiLicenseRestaurant = fssaiLicenseRestaurant;
    }

    public String getPoolRegistrationCertificate() {
        return poolRegistrationCertificate;
    }

    public void setPoolRegistrationCertificate(String poolRegistrationCertificate) {
        this.poolRegistrationCertificate = poolRegistrationCertificate;
    }

    public Boolean getWildlifeView() {
        return wildlifeView;
    }

    public void setWildlifeView(Boolean wildlifeView) {
        this.wildlifeView = wildlifeView;
    }

    public Boolean getEcoFriendlyCertification() {
        return ecoFriendlyCertification;
    }

    public void setEcoFriendlyCertification(Boolean ecoFriendlyCertification) {
        this.ecoFriendlyCertification = ecoFriendlyCertification;
    }

    public Boolean getRiverView() {
        return riverView;
    }

    public void setRiverView(Boolean riverView) {
        this.riverView = riverView;
    }

    public String getResortType() {
        return resortType;
    }

    public void setResortType(String resortType) {
        this.resortType = resortType;
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

    @Override
    public String toString() {
        return "Documents{" +
                "id=" + id +
                ", registrationLicense='" + registrationLicense + '\'' +
                ", ownerAadhaarCard='" + ownerAadhaarCard + '\'' +
                ", ownerPanCard='" + ownerPanCard + '\'' +
                ", hotelAccountDetails='" + hotelAccountDetails + '\'' +
                ", cancelledCheque='" + cancelledCheque + '\'' +
                ", fssaiLicenseRestaurant='" + fssaiLicenseRestaurant + '\'' +
                ", poolRegistrationCertificate='" + poolRegistrationCertificate + '\'' +
                ", wildlifeView=" + wildlifeView +
                ", ecoFriendlyCertification=" + ecoFriendlyCertification +
                ", riverView=" + riverView +
                ", resortType='" + resortType + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }
}
