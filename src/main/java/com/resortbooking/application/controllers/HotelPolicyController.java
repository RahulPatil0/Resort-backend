//package com.resortbooking.application.controllers;
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.resortbooking.application.dto.HotelPolicyDTO;
//import com.resortbooking.application.services.HotelPolicyService;
//
//@RestController
//@RequestMapping("/api/hotel-policies")
//public class HotelPolicyController {
//
//    private final HotelPolicyService policyService;
//
//    public HotelPolicyController(HotelPolicyService policyService) {
//        this.policyService = policyService;
//    }
//
//    @PostMapping
//    public ResponseEntity<HotelPolicyDTO> addPolicy(@RequestBody HotelPolicyDTO dto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(policyService.addPolicy(dto));
//    }
//
//    @GetMapping("/hotel/{hotelId}")
//    public ResponseEntity<List<HotelPolicyDTO>> getByHotel(@PathVariable Long hotelId) {
//        return ResponseEntity.ok(policyService.getPoliciesByHotel(hotelId));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
//        policyService.deletePolicy(id);
//        return ResponseEntity.noContent().build();
//    }
//}

package com.resortbooking.application.controllers;

import java.util.List;

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

import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.HotelPolicyService;

@RestController
@RequestMapping("/api/hotel-policies")
public class HotelPolicyController {

	@Autowired
	private HotelPolicyService policyService;

	private static final Logger logger = LoggerFactory.getLogger(HotelPolicyController.class);

	// 🔹 Add new policy
	@PostMapping
	public ResortBookingResponse addPolicy(@RequestBody HotelPolicyDTO dto) {
		String message = null;
		HttpStatus status = HttpStatus.CREATED;
		try {
			HotelPolicyDTO created = policyService.addPolicy(dto);
			logger.info("Hotel policy created successfully with ID {}", created.getId());
			message = "Hotel policy added successfully.";
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
			logger.error("Error adding hotel policy: {}", e.getMessage(), e);
		} catch (Exception e) {
			message = "Failed to add hotel policy: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error adding hotel policy: {}", e.getMessage(), e);
		}
		return new ResortBookingResponse(message, status);
	}

	// 🔹 Get policies by hotel ID
	@GetMapping("/hotel/{hotelId}")
	public ResortBookingResponse getByHotel(@PathVariable Long hotelId) {
		String message = null;
		HttpStatus status = HttpStatus.OK;
		try {
			List<HotelPolicyDTO> policies = policyService.getPoliciesByHotel(hotelId);
			if (policies.isEmpty()) {
				logger.warn("No policies found for hotel ID {}", hotelId);
				message = "No policies found for this hotel.";
				status = HttpStatus.NOT_FOUND;
			} else {
				message = "Policies retrieved successfully.";
			}
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
			logger.error("Error retrieving policies for hotel ID {}: {}", hotelId, e.getMessage(), e);
		} catch (Exception e) {
			message = "Failed to retrieve policies: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error retrieving policies for hotel ID {}: {}", hotelId, e.getMessage(), e);
		}
		return new ResortBookingResponse(message, status);
	}

	// 🔹 Delete policy by ID
	@DeleteMapping("/{id}")
	public ResortBookingResponse deletePolicy(@PathVariable Long id) {
		String message = null;
		HttpStatus status = HttpStatus.NO_CONTENT;
		try {
			policyService.deletePolicy(id);
			logger.info("Hotel policy ID {} deleted successfully", id);
			message = "Hotel policy deleted successfully.";
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
			logger.error("Error deleting policy ID {}: {}", id, e.getMessage(), e);
		} catch (Exception e) {
			message = "Failed to delete policy: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error deleting policy ID {}: {}", id, e.getMessage(), e);
		}
		return new ResortBookingResponse(message, status);
	}
}
