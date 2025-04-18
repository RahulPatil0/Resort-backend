package com.resortbooking.application.services.impl;

import com.resortbooking.application.dao.HotelReviewRepository;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.HotelReview;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.User;
import com.resortbooking.application.services.HotelReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelReviewServiceImpl implements HotelReviewService {

    @Autowired
    private HotelReviewRepository hotelReviewRepository;

    @Override
    public HotelReview saveReview(HotelReview review) throws ResortBookingException{
        try {
            return hotelReviewRepository.save(review);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error saving hotel review: " + e.getMessage());
            throw new ResortBookingException("Error saving hotel review: " + e.getMessage());
        }
    }

    @Override
    public Optional<HotelReview> getReviewById(Long id) throws ResortBookingException{
        try {
            return hotelReviewRepository.findById(id);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching hotel review by ID: " + e.getMessage());
            throw new ResortBookingException("Error fetching hotel review by ID: " + e.getMessage());
        }
    }

    @Override
    public List<HotelReview> getReviewsByHotel(Hotel hotel) throws ResortBookingException{
        try {
            return hotelReviewRepository.findByHotel(hotel);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching reviews by hotel: " + e.getMessage());
            throw new ResortBookingException("Error fetching reviews by hotel: " + e.getMessage());
        }
    }

    @Override
    public List<HotelReview> getRecentReviewsByHotel(Hotel hotel) throws ResortBookingException{
        try {
            return hotelReviewRepository.findByHotelOrderByCreatedAtDesc(hotel);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching recent reviews by hotel: " + e.getMessage());
            throw new ResortBookingException("Error fetching recent reviews by hotel: " + e.getMessage());
        }
    }

    @Override
    public List<HotelReview> getReviewsByUser(User user) throws ResortBookingException{
        try {
            return hotelReviewRepository.findByUser(user);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching reviews by user: " + e.getMessage());
            throw new ResortBookingException("Error fetching reviews by user: " + e.getMessage());
        }
    }

    @Override
    public void deleteReview(Long id) throws ResortBookingException{
        try {
            hotelReviewRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error deleting hotel review: " + e.getMessage());
            throw new ResortBookingException("Error deleting hotel review: " + e.getMessage());
        }
    }
}
