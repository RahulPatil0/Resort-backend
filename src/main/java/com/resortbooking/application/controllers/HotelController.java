package com.resortbooking.application.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	@Autowired
	private HotelService hotelService;

	@PostMapping
	public ResortBookingResponse createHotel(@RequestBody HotelDto hotelDto) {
		String message = null;
		HttpStatus status = HttpStatus.OK;
		try {
			HotelDto createdHotel = hotelService.createHotel(hotelDto);
			logger.info("Hotel created successfully with ID {}", createdHotel.getId());
			message = "Hotel created successfully";
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
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
		String message = null;
		HttpStatus status = HttpStatus.OK;
		try {
			List<HotelDto> hotels = hotelService.getAllHotels();
			logger.info("Fetched {} hotels", hotels.size());
			message = "Fetched " + hotels.size() + " hotels";
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
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
		String message = null;
		HttpStatus status = HttpStatus.OK;
		try {
			HotelDto hotel = hotelService.getHotelById(id);
			if (hotel != null) {
				logger.info("Fetched hotel with ID {}", id);
				message = "Fetched hotel with ID " + id;
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
}
