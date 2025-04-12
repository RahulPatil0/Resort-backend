package com.resortbooking.application.services;

import java.util.List;

import com.resortbooking.application.dto.AmenityDTO;

public interface AmenityService {
    AmenityDTO createAmenity(AmenityDTO amenityDTO);
    List<AmenityDTO> getAllAmenities();
    AmenityDTO getAmenityById(Long id);
    void deleteAmenity(Long id);
}
