package com.resortbooking.application.services;

import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HotelBookingService {

    HotelBooking saveBooking(HotelBooking booking);

    Optional<HotelBooking> getBookingById(Long id);

    List<HotelBooking> getAllBookings();

    List<HotelBooking> getBookingsByUser(User user);

    List<HotelBooking> getBookingsByHotel(Hotel hotel);

    List<HotelBooking> getBookingsByBookingStatus(String status);

    List<HotelBooking> getBookingsByPaymentStatus(String paymentStatus);

    List<HotelBooking> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate);

    List<HotelBooking> getBookingsByUserId(Long userId);

    List<HotelBooking> getBookingsByHotelId(Long hotelId);

    List<HotelBooking> getBookingsByStatusAndHotelId(String bookingStatus, Long hotelId);

    void deleteBooking(Long id);
}
