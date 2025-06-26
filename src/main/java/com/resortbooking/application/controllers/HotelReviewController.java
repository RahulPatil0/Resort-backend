//package com.resortbooking.application.controllers;
//
//import com.resortbooking.application.dto.HotelDto;
//import com.resortbooking.application.models.Hotel;
//import com.resortbooking.application.models.HotelReview;
//import com.resortbooking.application.models.User;
//import com.resortbooking.application.services.HotelReviewService;
//import com.resortbooking.application.services.HotelService;
//import com.resortbooking.application.services.UserService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/reviews")
//public class HotelReviewController {
//
//    @Autowired
//    private HotelReviewService reviewService;
//
//    @Autowired
//    private HotelService hotelService;
//
//    @Autowired
//    private UserService userService;
//
//    // 🔹 Create or update a review
//    @PostMapping
//    public ResponseEntity<?> saveReview(@RequestBody HotelReview review) {
//        if (review.getHotel() == null || review.getUser() == null) {
//            return ResponseEntity.badRequest().body("Hotel and User must be provided");
//        }
//
//        if (review.getId() == null) {
//            review.setCreatedAt(LocalDateTime.now());
//        }
//        review.setUpdatedAt(LocalDateTime.now());
//
//        HotelReview saved = reviewService.saveReview(review);
//        return ResponseEntity.ok(saved);
//    }
//
//    // 🔹 Get review by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
//        Optional<HotelReview> reviewOpt = reviewService.getReviewById(id);
//        return reviewOpt.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // 🔹 Get reviews by hotel ID
//    @GetMapping("/hotel/{hotelId}")
//    public ResponseEntity<?> getReviewsByHotel(@PathVariable Long hotelId) {
//        HotelDto hotelDto = hotelService.getHotelById(hotelId);
//        if (hotelDto == null) return ResponseEntity.notFound().build();
//
//        Hotel hotel = convertDtoToEntity(hotelDto);
//        List<HotelReview> reviews = reviewService.getReviewsByHotel(hotel);
//        return ResponseEntity.ok(reviews);
//    }
//
//    // 🔹 Get recent reviews by hotel ID
//    @GetMapping("/hotel/{hotelId}/recent")
//    public ResponseEntity<?> getRecentReviewsByHotel(@PathVariable Long hotelId) {
//        HotelDto hotelDto = hotelService.getHotelById(hotelId);
//        if (hotelDto == null) return ResponseEntity.notFound().build();
//
//        Hotel hotel = convertDtoToEntity(hotelDto);
//        List<HotelReview> reviews = reviewService.getRecentReviewsByHotel(hotel);
//        return ResponseEntity.ok(reviews);
//    }
//
//    // 🔹 Get reviews by user ID
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<?> getReviewsByUser(@PathVariable Long userId) {
//        Optional<User> userOpt = userService.getUserById(userId);
//        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();
//
//        List<HotelReview> reviews = reviewService.getReviewsByUser(userOpt.get());
//        return ResponseEntity.ok(reviews);
//    }
//
//    // 🔹 Delete a review by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
//        Optional<HotelReview> reviewOpt = reviewService.getReviewById(id);
//        if (reviewOpt.isEmpty()) return ResponseEntity.notFound().build();
//
//        reviewService.deleteReview(id);
//        return ResponseEntity.ok("Review deleted successfully");
//    }
//
//    // 🔁 Convert HotelDto to Hotel entity
//    private Hotel convertDtoToEntity(HotelDto dto) {
//        Hotel hotel = new Hotel();
//        hotel.setId(dto.getId());
//        hotel.setHotelName(dto.getHotelName());
//        hotel.setAddress(dto.getAddress());
//        hotel.setDescription(dto.getDescription());
//        hotel.setPricePerNight(dto.getPricePerNight());
//        hotel.setRating(dto.getRating());
//        hotel.setImageUrl(dto.getImageUrl());
//        hotel.setIsAvailable(dto.getIsAvailable());
//        hotel.setWebsite(dto.getWebsite());
//        hotel.setLatitude(dto.getLatitude());
//        hotel.setLongitude(dto.getLongitude());
//        return hotel;
//    }
//}
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
import com.resortbooking.application.mappers.HotelMapper;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelReview;
import com.resortbooking.application.models.User;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.HotelReviewService;
import com.resortbooking.application.services.HotelService;
import com.resortbooking.application.services.UserService;

@RestController
@RequestMapping("/api/reviews")
public class HotelReviewController {

	@Autowired
	private HotelReviewService reviewService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(HotelReviewController.class);

	// 🔹 Create or update a review
	@PostMapping
	public ResortBookingResponse saveReview(@RequestBody HotelReview review) {
		String message = null;
		HttpStatus status = HttpStatus.OK;

		try {
			if (review.getHotel() == null || review.getUser() == null) {
				message = "Hotel and User must be provided";
				status = HttpStatus.BAD_REQUEST;
				logger.warn("Hotel or User is missing in the review submission.");
			} else {
				if (review.getId() == null) {
					review.setCreatedAt(LocalDateTime.now());
				}
				review.setUpdatedAt(LocalDateTime.now());
				HotelReview saved = reviewService.saveReview(review);
				message = "Review saved successfully.";
				logger.info("Review saved successfully with ID {}", saved.getId());
			}
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
			logger.error("Error saving review: {}", e.getMessage(), e);
		} catch (Exception e) {
			message = "Error while saving review: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error saving review: {}", e.getMessage(), e);
		}

		return new ResortBookingResponse(message, status);
	}

	// 🔹 Get review by ID
	@GetMapping("/{id}")
	public ResortBookingResponse getReviewById(@PathVariable Long id) {
		String message = null;
		HttpStatus status = HttpStatus.OK;

		try {
			Optional<HotelReview> reviewOpt = reviewService.getReviewById(id);
			if (reviewOpt.isPresent()) {
				message = "Review retrieved successfully.";
				logger.info("Fetched review with ID {}", id);
			} else {
				message = "Review not found";
				status = HttpStatus.NOT_FOUND;
				logger.warn("Review with ID {} not found", id);
			}
		} catch (Exception e) {
			message = "Error while retrieving review: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error retrieving review with ID {}: {}", id, e.getMessage(), e);
		}

		return new ResortBookingResponse(message, status);
	}

	// 🔹 Get reviews by hotel ID
	@GetMapping("/hotel/{hotelId}")
	public ResortBookingResponse getReviewsByHotel(@PathVariable Long hotelId) {
		String message = null;
		HttpStatus status = HttpStatus.OK;

		try {
			HotelDto hotelDto = hotelService.getHotelById(hotelId);
			if (hotelDto != null) {
				Hotel hotel = HotelMapper.toEntity(hotelDto);
				List<HotelReview> reviews = reviewService.getReviewsByHotel(hotel);
				message = "Reviews retrieved successfully.";
				logger.info("Fetched reviews for hotel ID {}", hotelId);
			} else {
				message = "Hotel not found";
				status = HttpStatus.NOT_FOUND;
				logger.warn("Hotel with ID {} not found while fetching reviews", hotelId);
			}
		} catch (Exception e) {
			message = "Error while retrieving hotel reviews: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error retrieving hotel reviews for hotel ID {}: {}", hotelId, e.getMessage(), e);
		}

		return new ResortBookingResponse(message, status);
	}
	
	// 🔹 Get reviews by user ID
	@GetMapping("/user/{userId}")
	public ResortBookingResponse getReviewsByUser(@PathVariable Long userId) {
		String message = null;
		HttpStatus status = HttpStatus.OK;

		try {
			Optional<User> userOpt = userService.getUserById(userId);
			if (userOpt.isPresent()) {
				List<HotelReview> reviews = reviewService.getReviewsByUser(userOpt.get());
				message = "User reviews retrieved successfully.";
				logger.info("Fetched reviews for user ID {}", userId);
			} else {
				message = "User not found";
				status = HttpStatus.NOT_FOUND;
				logger.warn("User with ID {} not found while fetching reviews", userId);
			}
		} catch (Exception e) {
			message = "Error while retrieving user reviews: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error retrieving user reviews for user ID {}: {}", userId, e.getMessage(), e);
		}

		return new ResortBookingResponse(message, status);
	}

	// 🔹 Delete a review by ID
	@DeleteMapping("/{id}")
	public ResortBookingResponse deleteReview(@PathVariable Long id) {
		String message = null;
		HttpStatus status = HttpStatus.NO_CONTENT;

		try {
			Optional<HotelReview> reviewOpt = reviewService.getReviewById(id);
			if (reviewOpt.isPresent()) {
				reviewService.deleteReview(id);
				message = "Review deleted successfully";
				logger.info("Review ID {} deleted successfully", id);
			} else {
				message = "Review not found";
				status = HttpStatus.NOT_FOUND;
				logger.warn("Attempted to delete non-existent review ID {}", id);
			}
		} catch (Exception e) {
			message = "Error while deleting review: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error deleting review ID {}: {}", id, e.getMessage(), e);
		}

		return new ResortBookingResponse(message, status);
	}
}
