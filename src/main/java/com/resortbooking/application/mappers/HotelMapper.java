package com.resortbooking.application.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.dto.HotelPhotosDto;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;

public class HotelMapper {

    public static HotelDto toDto(Hotel hotel) {
        HotelDto dto = new HotelDto();
        
        BeanUtils.copyProperties(hotel, dto);
        
        List<HotelPhotosDto> hotelPhotosDtos = new ArrayList<>();
        
        for(HotelPhotos hotelPhotos : hotel.getHotelPhotos()) {
        	HotelPhotosDto hotelPhotosDto = HotelPhotosMapper.toDto(hotelPhotos);
        	hotelPhotosDtos.add(hotelPhotosDto);
        }
        dto.setHotelPhotos(hotelPhotosDtos);
        
        return dto;
    }

    public static Hotel toEntity(HotelDto dto) {
        Hotel hotel = new Hotel();

        BeanUtils.copyProperties(dto, hotel);
        
        return hotel;
    }
}
