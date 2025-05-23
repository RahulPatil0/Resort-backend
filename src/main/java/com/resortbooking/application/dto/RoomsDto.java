package com.resortbooking.application.dto;

import java.math.BigDecimal;

/**
 * DTO for hotel room data.
 */
public class RoomsDto {

    private Long id;
    private String roomNumber;
    private String type;
    private BigDecimal pricePerNight;
    private String status;
    private Integer capacity;
    private String description;
    private HotelDto hotel;

    // Constructors

    public RoomsDto() {
    }

    public RoomsDto(Long id, String roomNumber, String type, BigDecimal pricePerNight,
                    String status, Integer capacity, String description, HotelDto hotel) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.capacity = capacity;
        this.description = description;
        this.hotel = hotel;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HotelDto getHotel() {
        return hotel;
    }

    public void setHotel(HotelDto hotel) {
        this.hotel = hotel;
    }

    // toString

    @Override
    public String toString() {
        return "RoomsDTO{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", type='" + type + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", status='" + status + '\'' +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", hotelId=" + (hotel != null ? hotel.getId() : null) +
                '}';
    }
}
