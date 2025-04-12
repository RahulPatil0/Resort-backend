package com.resortbooking.application.controllers;

import com.resortbooking.application.dto.AmenityDTO;
import com.resortbooking.application.services.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    @PostMapping
    public ResponseEntity<?> createAmenity(@RequestBody AmenityDTO amenityDTO) {
        try {
            AmenityDTO createdAmenity = amenityService.createAmenity(amenityDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAmenity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error creating amenity: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAmenities() {
        try {
            List<AmenityDTO> amenities = amenityService.getAllAmenities();
            return ResponseEntity.ok(amenities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error retrieving amenities: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAmenityById(@PathVariable Long id) {
        try {
            AmenityDTO amenity = amenityService.getAmenityById(id);
            return ResponseEntity.ok(amenity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Amenity not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAmenity(@PathVariable Long id) {
        try {
            amenityService.deleteAmenity(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error deleting amenity with ID: " + id);
        }
    }
}
