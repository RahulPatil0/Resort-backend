package com.resortbooking.application.dto;

public class DocumentsDTO {

    private Long id;
    private String registrationLicense;
    private String ownerAadhaarCard;
    private String ownerPanCard;
    private String hotelAccountDetails;
    private String cancelledCheque;
    private String fssaiLicenseRestaurant;
    private String poolRegistrationCertificate;
    private Boolean wildlifeView;
    private Boolean ecoFriendlyCertification;
    private Boolean riverView;
    private String resortType;

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

    @Override
    public String toString() {
        return "DocumentsDto{" +
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
                '}';
    }
}
