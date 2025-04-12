package com.resortbooking.application.dao;

import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.User;
import com.resortbooking.application.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {

    List<HotelBooking> findByUser(User user);

    List<HotelBooking> findByHotel(Hotel hotel);

    List<HotelBooking> findByBookingStatus(String bookingStatus);

    List<HotelBooking> findByPaymentStatus(String paymentStatus);

    List<HotelBooking> findByCheckInDateBetween(LocalDate startDate, LocalDate endDate);

    List<HotelBooking> findByUserId(Long userId);

    List<HotelBooking> findByHotelId(Long hotelId);

    List<HotelBooking> findByBookingStatusAndHotelId(String bookingStatus, Long hotelId);
}
