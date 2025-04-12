package com.resortbooking.application.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.services.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody HotelDto hotelDto) {
        try {
            HotelDto createdHotel = hotelService.createHotel(hotelDto);
            logger.info("Hotel created successfully with ID {}", createdHotel.getId());
            return ResponseEntity.ok(createdHotel);
        } catch (Exception e) {
            logger.error("Error occurred while creating hotel: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("An error occurred while creating the hotel.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        try {
            List<HotelDto> hotels = hotelService.getAllHotels();
            logger.info("Fetched {} hotels", hotels.size());
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            logger.error("Error occurred while fetching hotels: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("An error occurred while fetching the hotels.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable Long id) {
        try {
            HotelDto dto = hotelService.getHotelById(id);
            if (dto != null) {
                logger.info("Fetched hotel with ID {}", id);
                return ResponseEntity.ok(dto);
            } else {
                logger.warn("Hotel with ID {} not found", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching hotel by ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("An error occurred while fetching the hotel.");
        }
    }
}
