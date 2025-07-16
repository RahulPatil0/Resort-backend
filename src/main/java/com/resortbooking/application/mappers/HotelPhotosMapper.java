package com.resortbooking.application.mappers;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.HotelPhotosDto;
import com.resortbooking.application.models.Media;

public class HotelPhotosMapper {

    public static HotelPhotosDto toDto(Media media) {
        
    	HotelPhotosDto dto = new HotelPhotosDto();
    	
    	BeanUtils.copyProperties(media, dto);
    	
    	return dto;
    }

    public static Media toEntity(HotelPhotosDto dto) {

        Media media = new Media();
        
        BeanUtils.copyProperties(dto, media);

        return media;
    }
}
