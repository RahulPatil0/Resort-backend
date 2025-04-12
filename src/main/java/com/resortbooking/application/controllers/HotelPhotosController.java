package com.resortbooking.application.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;
import com.resortbooking.application.services.HotelPhotosService;
import com.resortbooking.application.services.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotel-photos")
public class HotelPhotosController {

    private static final Logger logger = LoggerFactory.getLogger(HotelPhotosController.class);

    @Autowired
    private HotelPhotosService hotelPhotosService;

    @Autowired
    private HotelService hotelService;

    @PostMapping("/hotel/{hotelId}")
    public ResponseEntity<?> uploadPhoto(@PathVariable Long hotelId, @Valid @RequestBody HotelPhotos photoRequest) {
        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                logger.warn("Hotel ID {} not found", hotelId);
                return ResponseEntity.badRequest().body("Hotel not found.");
            }

            if (photoRequest.getImageUrl() == null || photoRequest.getImageUrl().trim().isEmpty()) {
                logger.warn("imageUrl is missing in the request for Hotel ID {}", hotelId);
                return ResponseEntity.badRequest().body("Image URL is required.");
            }

            Hotel hotel = convertDtoToEntity(hotelDto);
            photoRequest.setHotel(hotel);
            photoRequest.setUploadedAt(LocalDateTime.now());

            HotelPhotos saved = hotelPhotosService.savePhoto(photoRequest);

            logger.info("Photo uploaded for Hotel ID {}: {}", hotelId, saved.getId());
            return ResponseEntity.created(URI.create("/api/hotel-photos/" + saved.getId())).body(saved);
        } catch (Exception e) {
            logger.error("Error uploading photo for hotel ID {}: {}", hotelId, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to upload photo: " + e.getMessage());
        }
    }

    @GetMapping("/{photoId}")
    public ResponseEntity<?> getPhoto(@PathVariable Long photoId) {
        try {
            return hotelPhotosService.getPhotoById(photoId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> {
                        logger.warn("Photo ID {} not found", photoId);
                        return ResponseEntity.notFound().build();
                    });
        } catch (Exception e) {
            logger.error("Error retrieving photo ID {}: {}", photoId, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to retrieve photo: " + e.getMessage());
        }
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<?> getPhotosByHotel(@PathVariable Long hotelId) {
        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                logger.warn("Hotel ID {} not found while fetching photos", hotelId);
                return ResponseEntity.badRequest().body("Hotel not found.");
            }

            Hotel hotel = convertDtoToEntity(hotelDto);
            List<HotelPhotos> photos = hotelPhotosService.getPhotosByHotelSorted(hotel);
            return ResponseEntity.ok(photos);
        } catch (Exception e) {
            logger.error("Error retrieving photos for hotel ID {}: {}", hotelId, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to retrieve photos: " + e.getMessage());
        }
    }

    @GetMapping("/hotel/{hotelId}/primary")
    public ResponseEntity<?> getPrimaryPhoto(@PathVariable Long hotelId) {
        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                logger.warn("Hotel ID {} not found while fetching primary photo", hotelId);
                return ResponseEntity.badRequest().body("Hotel not found.");
            }

            Hotel hotel = convertDtoToEntity(hotelDto);
            return hotelPhotosService.getPrimaryPhotoByHotel(hotel)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> {
                        logger.info("No primary photo set for hotel ID {}", hotelId);
                        return ResponseEntity.noContent().build();
                    });
        } catch (Exception e) {
            logger.error("Error retrieving primary photo for hotel ID {}: {}", hotelId, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to retrieve primary photo: " + e.getMessage());
        }
    }

    @DeleteMapping("/{photoId}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long photoId) {
        try {
            return hotelPhotosService.getPhotoById(photoId)
                    .map(photo -> {
                        hotelPhotosService.deletePhoto(photoId);
                        logger.info("Photo ID {} deleted successfully", photoId);
                        return ResponseEntity.noContent().build();
                    })
                    .orElseGet(() -> {
                        logger.warn("Attempted to delete non-existent photo ID {}", photoId);
                        return ResponseEntity.notFound().build();
                    });
        } catch (Exception e) {
            logger.error("Error deleting photo ID {}: {}", photoId, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to delete photo: " + e.getMessage());
        }
    }

    private Hotel convertDtoToEntity(HotelDto dto) {
        Hotel hotel = new Hotel();
        hotel.setId(dto.getId());
        hotel.setHotelName(dto.getHotelName());
        hotel.setAddress(dto.getAddress());
        hotel.setDescription(dto.getDescription());
        hotel.setPricePerNight(dto.getPricePerNight());
        hotel.setRating(dto.getRating());
        hotel.setImageUrl(dto.getImageUrl());
        hotel.setIsAvailable(dto.getIsAvailable());
        hotel.setWebsite(dto.getWebsite());
        hotel.setLatitude(dto.getLatitude());
        hotel.setLongitude(dto.getLongitude());
        return hotel;
    }
}
