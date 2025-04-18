package com.resortbooking.application.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;
import com.resortbooking.application.response.ResortBookingResponse;
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
	public ResortBookingResponse uploadPhoto(@PathVariable Long hotelId, @Valid @RequestBody HotelPhotos photoRequest) {
		String message = "";
		HttpStatus status = HttpStatus.OK;
		try {
			HotelDto hotelDto = hotelService.getHotelById(hotelId);
			if (hotelDto == null) {
				logger.warn("Hotel ID {} not found", hotelId);
				message = "Hotel not found.";
				status = HttpStatus.BAD_REQUEST;
			} else if (photoRequest.getImageUrl() == null || photoRequest.getImageUrl().trim().isEmpty()) {
				logger.warn("imageUrl is missing in the request for Hotel ID {}", hotelId);
				message = "Image URL is required.";
				status = HttpStatus.BAD_REQUEST;
			} else {
				Hotel hotel = convertDtoToEntity(hotelDto);
				photoRequest.setHotel(hotel);
				photoRequest.setUploadedAt(LocalDateTime.now());

				HotelPhotos saved = hotelPhotosService.savePhoto(photoRequest);

				logger.info("Photo uploaded for Hotel ID {}: {}", hotelId, saved.getId());
				message = "Photo uploaded successfully.";
				status = HttpStatus.CREATED;
			}
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
			logger.error("Error uploading photo for hotel ID {}: {}", hotelId, e.getMessage(), e);
		} catch (Exception e) {
			message = "Failed to upload photo: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error uploading photo for hotel ID {}: {}", hotelId, e.getMessage(), e);
		}
		return new ResortBookingResponse(message, status);
	}

	@GetMapping("/{photoId}")
	public ResortBookingResponse getPhoto(@PathVariable Long photoId) {
		String message = "";
		HttpStatus status = HttpStatus.OK;
		try {
			Optional<HotelPhotos> photo = hotelPhotosService.getPhotoById(photoId);
			if (photo.isPresent()) {
				logger.info("Fetched photo ID {}", photoId);
				message = "Photo retrieved successfully.";
			} else {
				logger.warn("Photo ID {} not found", photoId);
				message = "Photo not found.";
				status = HttpStatus.NOT_FOUND;
			}
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
			logger.error("Error retrieving photo ID {}: {}", photoId, e.getMessage(), e);
		} catch (Exception e) {
			message = "Failed to retrieve photo: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error retrieving photo ID {}: {}", photoId, e.getMessage(), e);
		}
		return new ResortBookingResponse(message, status);
	}

	@GetMapping("/hotel/{hotelId}")
	public ResortBookingResponse getPhotosByHotel(@PathVariable Long hotelId) {
		String message = "";
		HttpStatus status = HttpStatus.OK;
		try {
			HotelDto hotelDto = hotelService.getHotelById(hotelId);
			if (hotelDto == null) {
				logger.warn("Hotel ID {} not found while fetching photos", hotelId);
				message = "Hotel not found.";
				status = HttpStatus.BAD_REQUEST;
			} else {
				Hotel hotel = convertDtoToEntity(hotelDto);
				List<HotelPhotos> photos = hotelPhotosService.getPhotosByHotelSorted(hotel);
				message = "Photos retrieved successfully.";
			}
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
			logger.error("Error retrieving photos for hotel ID {}: {}", hotelId, e.getMessage(), e);
		} catch (Exception e) {
			message = "Failed to retrieve photos: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error retrieving photos for hotel ID {}: {}", hotelId, e.getMessage(), e);
		}
		return new ResortBookingResponse(message, status);
	}

	@GetMapping("/hotel/{hotelId}/primary")
	public ResortBookingResponse getPrimaryPhoto(@PathVariable Long hotelId) {
		String message = "";
		HttpStatus status = HttpStatus.OK;
		try {
			HotelDto hotelDto = hotelService.getHotelById(hotelId);
			if (hotelDto == null) {
				logger.warn("Hotel ID {} not found while fetching primary photo", hotelId);
				message = "Hotel not found.";
				status = HttpStatus.BAD_REQUEST;
			} else {
				Hotel hotel = convertDtoToEntity(hotelDto);
				Optional<HotelPhotos> primaryPhoto = hotelPhotosService.getPrimaryPhotoByHotel(hotel);
				if (primaryPhoto.isPresent()) {
					message = "Primary photo retrieved successfully.";
				} else {
					message = "No primary photo set for hotel.";
					status = HttpStatus.NO_CONTENT;
					logger.info("No primary photo set for hotel ID {}", hotelId);
				}
			}
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
			logger.error("Error retrieving primary photo for hotel ID {}: {}", hotelId, e.getMessage(), e);
		} catch (Exception e) {
			message = "Failed to retrieve primary photo: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error retrieving primary photo for hotel ID {}: {}", hotelId, e.getMessage(), e);
		}
		return new ResortBookingResponse(message, status);
	}

	@DeleteMapping("/{photoId}")
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
				logger.warn("Attempted to delete non-existent photo ID {}", photoId);
				message = "Photo not found.";
				status = HttpStatus.NOT_FOUND;
			}
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
			logger.error("Error deleting photo ID {}: {}", photoId, e.getMessage(), e);
		} catch (Exception e) {
			message = "Failed to delete photo: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error deleting photo ID {}: {}", photoId, e.getMessage(), e);
		}
		return new ResortBookingResponse(message, status);
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
