package com.resortbooking.application.dao;

import com.resortbooking.application.models.HotelReview;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelReviewRepository extends JpaRepository<HotelReview, Long> {
    
    List<HotelReview> findByHotel(Hotel hotel);

    List<HotelReview> findByUser(User user);

    List<HotelReview> findByHotelOrderByCreatedAtDesc(Hotel hotel); // For recent reviews
}
