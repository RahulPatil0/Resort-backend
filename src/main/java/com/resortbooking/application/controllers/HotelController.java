package com.resortbooking.application.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.HotelMapper;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.HotelPhotosService;
import com.resortbooking.application.services.HotelPolicyService;
import com.resortbooking.application.services.HotelService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/hotels")
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelPhotosService hotelPhotosService;

    @Autowired
    private HotelPolicyService hotelPolicyService;

    // ------------------ Hotel Endpoints ------------------

    @PostMapping("/create")
    public ResortBookingResponse createHotel(@RequestBody HotelDto hotelDto) {
        String message;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        try {
            message = hotelService.createHotel(hotelDto);
            status = HttpStatus.OK;
        } catch (ResortBookingException e) {
            message = e.getMessage();
            logger.error("Error occurred while creating hotel: {}", e.getMessage(), e);
        } catch (Exception e) {
            message = "Error creating hotel: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error occurred while creating hotel: {}", e.getMessage(), e);
        }
        return new ResortBookingResponse(message, status);
    }

    @GetMapping
    public ResortBookingResponse getAllHotels() {
        String message;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        try {
            List<HotelDto> hotels = hotelService.getAllHotels();
            logger.info("Fetched {} hotels", hotels.size());
            message = "Fetched " + hotels.size() + " hotels";
            status = HttpStatus.OK;
            return new ResortBookingResponse(hotels, status);
        } catch (ResortBookingException e) {
            message = e.getMessage();
            logger.error("Error occurred while fetching hotels: {}", e.getMessage(), e);
        } catch (Exception e) {
            message = "Error fetching hotels: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error occurred while fetching hotels: {}", e.getMessage(), e);
        }
        return new ResortBookingResponse(message, status);
    }

    @GetMapping("/{id}")
    public ResortBookingResponse getHotelById(@PathVariable Long id) {
        String message;
        HttpStatus status = HttpStatus.OK;
        try {
            HotelDto hotel = hotelService.getHotelById(id);
            if (hotel != null) {
                logger.info("Fetched hotel with ID {}", id);
                message = "Fetched hotel with ID " + id;
                return new ResortBookingResponse(hotel, status);
            } else {
                message = "Hotel not found";
                status = HttpStatus.NOT_FOUND;
                logger.warn("Hotel with ID {} not found", id);
            }
        } catch (ResortBookingException e) {
            message = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
            logger.error("Error fetching hotel by ID {}: {}", id, e.getMessage(), e);
        } catch (Exception e) {
            message = "Error fetching hotel with ID " + id + ": " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error fetching hotel by ID {}: {}", id, e.getMessage(), e);
        }
        return new ResortBookingResponse(message, status);
    }
    @PutMapping("/{id}")
    public ResortBookingResponse updateHotel(@PathVariable Long id, @RequestBody HotelDto hotelDto) {
        String message;
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {
            HotelDto existingHotel = hotelService.getHotelById(id);

            if (existingHotel == null) {
                message = "Hotel not found with ID: " + id;
                status = HttpStatus.NOT_FOUND;
                logger.warn("Hotel with ID {} not found for update", id);
            } else {
                hotelDto.setId(id); // Set the ID to ensure update
                message = hotelService.updateHotel(id, hotelDto);
                status = HttpStatus.OK;
                logger.info("Hotel with ID {} updated successfully", id);
            }
        } catch (ResortBookingException e) {
            message = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
            logger.error("Error updating hotel with ID {}: {}", id, e.getMessage(), e);
        } catch (Exception e) {
            message = "Error updating hotel with ID " + id + ": " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error updating hotel with ID {}: {}", id, e.getMessage(), e);
        }

        return new ResortBookingResponse(message, status);
    }



    // ------------------ Hotel Photos Endpoints ------------------

    @PostMapping("/photos/hotel/{hotelId}")
    public ResortBookingResponse uploadPhoto(@PathVariable Long hotelId, @Valid @RequestBody HotelPhotos photoRequest) {
        String message = "";
        HttpStatus status = HttpStatus.OK;
        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                logger.warn("Hotel ID {} not found", hotelId);
                message = "Hotel not found.";
                status = HttpStatus.BAD_REQUEST;
            } else if (photoRequest.getPhotoUrl() == null || photoRequest.getPhotoUrl().trim().isEmpty()) {
                logger.warn("imageUrl is missing in the request for Hotel ID {}", hotelId);
                message = "Image URL is required.";
                status = HttpStatus.BAD_REQUEST;
            } else {
                Hotel hotel = HotelMapper.toEntity(hotelDto);
                photoRequest.setHotel(hotel);
                photoRequest.setUploadedAt(LocalDateTime.now());

                HotelPhotos saved = hotelPhotosService.savePhoto(photoRequest);

                logger.info("Photo uploaded for Hotel ID {}: {}", hotelId, saved.getId());
                message = "Photo uploaded successfully.";
                status = HttpStatus.CREATED;
            }
        } catch (Exception e) {
            message = "Failed to upload photo: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error uploading photo for hotel ID {}: {}", hotelId, e.getMessage(), e);
        }
        return new ResortBookingResponse(message, status);
    }

    @GetMapping("/photos/{photoId}")
    public ResortBookingResponse getPhoto(@PathVariable Long photoId) {
        String message = "";
        HttpStatus status = HttpStatus.OK;
        try {
            Optional<HotelPhotos> photo = hotelPhotosService.getPhotoById(photoId);
            if (photo.isPresent()) {
                logger.info("Fetched photo ID {}", photoId);
                return new ResortBookingResponse(photo.get(), status);
            } else {
                message = "Photo not found.";
                status = HttpStatus.NOT_FOUND;
                logger.warn("Photo ID {} not found", photoId);
            }
        } catch (Exception e) {
            message = "Failed to retrieve photo: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error retrieving photo ID {}: {}", photoId, e.getMessage(), e);
        }
        return new ResortBookingResponse(message, status);
    }

    @GetMapping("/photos/hotel/{hotelId}")
    public ResortBookingResponse getPhotosByHotel(@PathVariable Long hotelId) {
        String message = "";
        HttpStatus status = HttpStatus.OK;
        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                message = "Hotel not found.";
                status = HttpStatus.BAD_REQUEST;
            } else {
                Hotel hotel = HotelMapper.toEntity(hotelDto);
                List<HotelPhotos> photos = hotelPhotosService.getPhotosByHotelSorted(hotel);
                return new ResortBookingResponse(photos, status);
            }
        } catch (Exception e) {
            message = "Failed to retrieve photos: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error retrieving photos for hotel ID {}: {}", hotelId, e.getMessage(), e);
        }
        return new ResortBookingResponse(message, status);
    }

    @DeleteMapping("/photos/{photoId}")
    public ResortBookingResponse deletePhoto(@PathVariable Long photoId) {
        String message = "";
        HttpStatus status = HttpStatus.NO_CONTENT;
        try {
            Optional<HotelPhotos> photo = hotelPhotosService.getPhotoById(photoId);
            if (photo.isPresent()) {
                hotelPhotosService.deletePhoto(photoId);
                logger.info("Photo ID {} deleted successfully", photoId);
                message = "Photo deleted successfully.";
            } else {
                message = "Photo not found.";
                status = HttpStatus.NOT_FOUND;
                logger.warn("Attempted to delete non-existent photo ID {}", photoId);
            }
        } catch (Exception e) {
            message = "Failed to delete photo: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error deleting photo ID {}: {}", photoId, e.getMessage(), e);
        }
        return new ResortBookingResponse(message, status);
    }

    // ------------------ Hotel Policy Endpoints ------------------

    @PostMapping("/policies")
    public ResortBookingResponse addPolicy(@RequestBody HotelPolicyDTO dto) {
        String message = null;
        HttpStatus status = HttpStatus.CREATED;
        try {
            HotelPolicyDTO created = hotelPolicyService.addPolicy(dto);
            logger.info("Hotel policy created successfully with ID {}", created.getId());
            message = "Hotel policy added successfully.";
        } catch (Exception e) {
            message = "Failed to add hotel policy: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error adding hotel policy: {}", e.getMessage(), e);
        }
        return new ResortBookingResponse(message, status);
    }

    @GetMapping("/policies/hotel/{hotelId}")
    public ResortBookingResponse getByHotel(@PathVariable Long hotelId) {
        String message = null;
        HttpStatus status = HttpStatus.OK;
        try {
            List<HotelPolicyDTO> policies = hotelPolicyService.getPoliciesByHotel(hotelId);
            if (policies.isEmpty()) {
                message = "No policies found for this hotel.";
                status = HttpStatus.NOT_FOUND;
                logger.warn("No policies found for hotel ID {}", hotelId);
            } else {
                message = "Policies retrieved successfully.";
                return new ResortBookingResponse(policies, status);
            }
        } catch (Exception e) {
            message = "Failed to retrieve policies: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error retrieving policies for hotel ID {}: {}", hotelId, e.getMessage(), e);
        }
        return new ResortBookingResponse(message, status);
    }

    @DeleteMapping("/policies/{id}")
    public ResortBookingResponse deletePolicy(@PathVariable Long id) {
        String message = null;
        HttpStatus status = HttpStatus.NO_CONTENT;
        try {
            hotelPolicyService.deletePolicy(id);
            logger.info("Hotel policy ID {} deleted successfully", id);
            message = "Hotel policy deleted successfully.";
        } catch (Exception e) {
            message = "Failed to delete policy: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Error deleting policy ID {}: {}", id, e.getMessage(), e);
        }
        return new ResortBookingResponse(message, status);
    }
    
@PutMapping("/policies/{id}")
public ResortBookingResponse updatePolicy(@PathVariable Long id, @RequestBody HotelPolicyDTO dto) {
    String message = null;
    HttpStatus status = HttpStatus.BAD_REQUEST;

    try {
        dto.setId(id); // Ensure the ID is set for update
        HotelPolicyDTO updatedPolicy = hotelPolicyService.updatePolicy(id, dto);
        message = "Hotel policy updated successfully.";
        status = HttpStatus.OK;
        logger.info("Hotel policy ID {} updated successfully", id);
        return new ResortBookingResponse(updatedPolicy, status);
    } catch (ResortBookingException e) {
        message = e.getMessage();
        logger.error("Error updating policy ID {}: {}", id, e.getMessage(), e);
        status = HttpStatus.BAD_REQUEST;
    } catch (Exception e) {
        message = "Failed to update policy: " + e.getMessage();
        status = HttpStatus.INTERNAL_SERVER_ERROR;
        logger.error("Error updating policy ID {}: {}", id, e.getMessage(), e);
    }

    return new ResortBookingResponse(message, status);
}
}

