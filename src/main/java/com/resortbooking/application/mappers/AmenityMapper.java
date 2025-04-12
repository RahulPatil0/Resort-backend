package com.resortbooking.application.mappers;

import com.resortbooking.application.dto.AmenityDTO;
import com.resortbooking.application.models.Amenity;
import org.springframework.stereotype.Component;

@Component
public class AmenityMapper {

    public AmenityDTO toDTO(Amenity amenity) {
        AmenityDTO dto = new AmenityDTO();
        dto.setId(amenity.getId());
        dto.setName(amenity.getName());
        dto.setCategory(amenity.getCategory());
        return dto;
    }

    public Amenity toEntity(AmenityDTO dto) {
        Amenity amenity = new Amenity();
        amenity.setId(dto.getId());
        amenity.setName(dto.getName());
        amenity.setCategory(dto.getCategory());
        return amenity;
    }
}
