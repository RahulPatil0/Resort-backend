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

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelReview;
import com.resortbooking.application.models.User;
import com.resortbooking.application.services.HotelReviewService;
import com.resortbooking.application.services.HotelService;
import com.resortbooking.application.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class HotelReviewController {

    @Autowired
    private HotelReviewService reviewService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    // 🔹 Create or update a review
    @PostMapping
    public ResponseEntity<?> saveReview(@RequestBody HotelReview review) {
        try {
            if (review.getHotel() == null || review.getUser() == null) {
                return ResponseEntity.badRequest().body("Hotel and User must be provided");
            }

            if (review.getId() == null) {
                review.setCreatedAt(LocalDateTime.now());
            }
            review.setUpdatedAt(LocalDateTime.now());

            HotelReview saved = reviewService.saveReview(review);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error while saving review: " + e.getMessage());
        }
    }

    // 🔹 Get review by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        try {
            Optional<HotelReview> reviewOpt = reviewService.getReviewById(id);
            return reviewOpt.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error while retrieving review: " + e.getMessage());
        }
    }

    // 🔹 Get reviews by hotel ID
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<?> getReviewsByHotel(@PathVariable Long hotelId) {
        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) return ResponseEntity.notFound().build();

            Hotel hotel = convertDtoToEntity(hotelDto);
            List<HotelReview> reviews = reviewService.getReviewsByHotel(hotel);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error while retrieving hotel reviews: " + e.getMessage());
        }
    }

    // 🔹 Get recent reviews by hotel ID
    @GetMapping("/hotel/{hotelId}/recent")
    public ResponseEntity<?> getRecentReviewsByHotel(@PathVariable Long hotelId) {
        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) return ResponseEntity.notFound().build();

            Hotel hotel = convertDtoToEntity(hotelDto);
            List<HotelReview> reviews = reviewService.getRecentReviewsByHotel(hotel);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error while retrieving recent hotel reviews: " + e.getMessage());
        }
    }

    // 🔹 Get reviews by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getReviewsByUser(@PathVariable Long userId) {
        try {
            Optional<User> userOpt = userService.getUserById(userId);
            if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

            List<HotelReview> reviews = reviewService.getReviewsByUser(userOpt.get());
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error while retrieving user reviews: " + e.getMessage());
        }
    }

    // 🔹 Delete a review by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        try {
            Optional<HotelReview> reviewOpt = reviewService.getReviewById(id);
            if (reviewOpt.isEmpty()) return ResponseEntity.notFound().build();

            reviewService.deleteReview(id);
            return ResponseEntity.ok("Review deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error while deleting review: " + e.getMessage());
        }
    }

    // 🔁 Convert HotelDto to Hotel entity
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

