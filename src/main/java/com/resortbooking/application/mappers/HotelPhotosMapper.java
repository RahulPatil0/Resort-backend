package com.resortbooking.application.mappers;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.HotelPhotosDto;
import com.resortbooking.application.models.HotelPhotos;

public class HotelPhotosMapper {

    public static HotelPhotosDto toDto(HotelPhotos hotelPhotos) {
        
    	HotelPhotosDto dto = new HotelPhotosDto();
    	
    	BeanUtils.copyProperties(hotelPhotos, dto);
    	
    	return dto;
    }

    public static HotelPhotos toEntity(HotelPhotosDto dto) {

        HotelPhotos hotelPhotos = new HotelPhotos();
        
        BeanUtils.copyProperties(dto, hotelPhotos);

        return hotelPhotos;
    }
}
