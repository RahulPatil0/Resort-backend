package com.resortbooking.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.dto.AmenityDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.AmenityService;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController {

	@Autowired
	private AmenityService amenityService;

	@PostMapping
	public ResortBookingResponse createAmenity(@RequestBody AmenityDTO amenityDTO) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";

		try {
			AmenityDTO createdAmenity = amenityService.createAmenity(amenityDTO);
			status = HttpStatus.CREATED;
			return new ResortBookingResponse(createdAmenity, status);
		} catch (ResortBookingException e) {
			message = e.getMessage();
		} catch (Exception e) {
			message = "Error creating amenity: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}

	@GetMapping
	public ResortBookingResponse getAllAmenities() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";

		try {
			List<AmenityDTO> amenities = amenityService.getAllAmenities();
			status = HttpStatus.OK;
			return new ResortBookingResponse(amenities, status);
		} catch (Exception e) {
			message = "Error retrieving amenities: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}

	@GetMapping("/{id}")
	public ResortBookingResponse getAmenityById(@PathVariable Long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";

		try {
			AmenityDTO amenity = amenityService.getAmenityById(id);
			status = HttpStatus.OK;
			return new ResortBookingResponse(amenity, status);
		} catch (ResortBookingException e) {
			message = e.getMessage();
		} catch (Exception e) {
			message = "Amenity not found with ID: " + id;
			status = HttpStatus.NOT_FOUND;
		}

		return new ResortBookingResponse(message, status);
	}

	@DeleteMapping("/{id}")
	public ResortBookingResponse deleteAmenity(@PathVariable Long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";

		try {
			amenityService.deleteAmenity(id);
			status = HttpStatus.OK;
			return new ResortBookingResponse("Amenity deleted successfully", status);
		} catch (ResortBookingException e) {
			message = e.getMessage();
		} catch (Exception e) {
			message = "Error deleting amenity with ID: " + id;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}
}
