package com.resortbooking.application.mappers;

import com.resortbooking.application.dto.AmenityDTO;
import com.resortbooking.application.models.Amenity;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AmenityMapper {

    public static AmenityDTO toDTO(Amenity amenity) {
        AmenityDTO dto = new AmenityDTO();
        BeanUtils.copyProperties(amenity, dto);
        return dto;
    }

    public static Amenity toEntity(AmenityDTO dto) {
        Amenity amenity = new Amenity();
        BeanUtils.copyProperties(dto, amenity);
        return amenity;
    }
}
