package com.resortbooking.application.mappers;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.dto.HotelPhotosDto;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;

public class HotelPhotosMapper {

    public static HotelPhotosDto toDto(HotelPhotos hotelPhotos) {
        if (hotelPhotos == null) return null;

        HotelDto hotelDTO = HotelMapper.toDto(hotelPhotos.getHotel());

        return new HotelPhotosDto(
                hotelPhotos.getId(),
                hotelPhotos.getImageUrl(),
                hotelPhotos.getUploadedAt(),
                hotelDTO
        );
    }

    public static HotelPhotos toEntity(HotelPhotosDto dto) {
        if (dto == null) return null;

        HotelPhotos hotelPhotos = new HotelPhotos();
        hotelPhotos.setId(dto.getId());
        hotelPhotos.setImageUrl(dto.getPhotoUrl());
        hotelPhotos.setUploadedAt(dto.getUploadedAt());

        Hotel hotel = HotelMapper.toEntity(dto.getHotel());
        hotelPhotos.setHotel(hotel);

        return hotelPhotos;
    }
}
