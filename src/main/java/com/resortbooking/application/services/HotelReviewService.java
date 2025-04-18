package com.resortbooking.application.services;

import com.resortbooking.application.models.HotelReview;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.User;

import java.util.List;
import java.util.Optional;

public interface HotelReviewService {

    HotelReview saveReview(HotelReview review) throws ResortBookingException;

    Optional<HotelReview> getReviewById(Long id) throws ResortBookingException;

    List<HotelReview> getReviewsByHotel(Hotel hotel) throws ResortBookingException;

    List<HotelReview> getRecentReviewsByHotel(Hotel hotel) throws ResortBookingException;

    List<HotelReview> getReviewsByUser(User user) throws ResortBookingException;

    void deleteReview(Long id) throws ResortBookingException;
}
