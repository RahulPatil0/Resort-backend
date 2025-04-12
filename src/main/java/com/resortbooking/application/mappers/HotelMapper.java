package com.resortbooking.application.mappers;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.models.Hotel;

public class HotelMapper {

    public static HotelDto toDto(Hotel hotel) {
        HotelDto dto = new HotelDto();
        dto.setId(hotel.getId());
        dto.setHotelName(hotel.getHotelName());
        dto.setAddress(hotel.getAddress());
        dto.setDescription(hotel.getDescription());
        dto.setPricePerNight(hotel.getPricePerNight());
        dto.setRating(hotel.getRating());
        dto.setImageUrl(hotel.getImageUrl());
        dto.setIsAvailable(hotel.getIsAvailable());
        dto.setWebsite(hotel.getWebsite());
        dto.setLatitude(hotel.getLatitude());
        dto.setLongitude(hotel.getLongitude());
        return dto;
    }

    public static Hotel toEntity(HotelDto dto) {
        Hotel hotel = new Hotel();
        hotel.setId(dto.getId());
        hotel.setHotelName(dto.getHotelName());
        hotel.setAddress(dto.getAddress());
        hotel.setDescription(dto.getDescription());
        hotel.setPricePerNight(dto.getPricePerNight());
        hotel.setRating(dto.getRating());
        hotel.setImageUrl(dto.getImageUrl());
        hotel.setIsAvailable(dto.getIsAvailable());
        hotel.setWebsite(dto.getWebsite());
        hotel.setLatitude(dto.getLatitude());
        hotel.setLongitude(dto.getLongitude());
        return hotel;
    }
}
