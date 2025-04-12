package com.resortbooking.application.mappers;

import com.resortbooking.application.dto.HotelReviewDto;
import com.resortbooking.application.models.HotelReview;

public class HotelReviewMapper {

    // Converts Entity to DTO
    public static HotelReviewDto toDto(HotelReview review) {
        if (review == null) return null;

        return new HotelReviewDto(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt(),
                UserMapper.toDto(review.getUser()),      // Convert User to UserDTO
                HotelMapper.toDto(review.getHotel())     // Convert Hotel to HotelDTO
        );
    }

    // Converts DTO to Entity
    public static HotelReview toEntity(HotelReviewDto dto) {
        if (dto == null) return null;

        HotelReview review = new HotelReview();
        review.setId(dto.getId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setCreatedAt(dto.getCreatedAt());

        review.setUser(UserMapper.toEntity(dto.getUser()));   // Convert UserDTO to User
        review.setHotel(HotelMapper.toEntity(dto.getHotel())); // Convert HotelDTO to Hotel

        return review;
    }
}
