package com.resortbooking.application.services;

import java.util.List;

import com.resortbooking.application.dto.AmenityDTO;
import com.resortbooking.application.exception.ResortBookingException;

public interface AmenityService {
    AmenityDTO createAmenity(AmenityDTO amenityDTO) throws ResortBookingException;
    
    List<AmenityDTO> getAllAmenities() throws ResortBookingException;
    
    AmenityDTO getAmenityById(Long id) throws ResortBookingException;
    
    void deleteAmenity(Long id) throws ResortBookingException;
}
