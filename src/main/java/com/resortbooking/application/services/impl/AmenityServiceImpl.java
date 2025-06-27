package com.resortbooking.application.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.AmenityRepository;
import com.resortbooking.application.dto.AmenityDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.AmenityMapper;
import com.resortbooking.application.models.Amenity;
import com.resortbooking.application.services.AmenityService;

@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    @Override
    public AmenityDTO createAmenity(AmenityDTO amenityDTO) throws ResortBookingException {
        try {
            Amenity amenity = AmenityMapper.toEntity(amenityDTO);
            return AmenityMapper.toDTO(amenityRepository.save(amenity));
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error creating amenity: " + e.getMessage());
            throw new ResortBookingException("Error creating amenity: " + e.getMessage());
        }
    }

    @Override
    public List<AmenityDTO> getAllAmenities() throws ResortBookingException{
        try {
            return amenityRepository.findAll().stream()
                    .map(AmenityMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching all amenities: " + e.getMessage());
            throw new ResortBookingException("Error fetching all amenities: " + e.getMessage());
        }
    }

    @Override
    public AmenityDTO getAmenityById(Long id) throws ResortBookingException{
        try {
            return amenityRepository.findById(id)
                    .map(AmenityMapper::toDTO)
                    .orElseThrow(() -> new RuntimeException("Amenity not found"));
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching amenity by ID: " + e.getMessage());
            throw new ResortBookingException("Error fetching amenity by ID: " + e.getMessage());
        }
    }

    @Override
    public void deleteAmenity(Long id) throws ResortBookingException{
        try {
            if (!amenityRepository.existsById(id)) {
                throw new RuntimeException("Amenity not found for deletion");
            }
            amenityRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error deleting amenity: " + e.getMessage());
            throw new ResortBookingException("Error deleting amenity: " + e.getMessage());
        }
    }
}
