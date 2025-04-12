package com.resortbooking.application.services;

import com.resortbooking.application.models.HotelReview;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.User;

import java.util.List;
import java.util.Optional;

public interface HotelReviewService {

    HotelReview saveReview(HotelReview review);

    Optional<HotelReview> getReviewById(Long id);

    List<HotelReview> getReviewsByHotel(Hotel hotel);

    List<HotelReview> getRecentReviewsByHotel(Hotel hotel);

    List<HotelReview> getReviewsByUser(User user);

    void deleteReview(Long id);
}
