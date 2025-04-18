package com.resortbooking.application.services;

import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HotelBookingService {

    HotelBooking saveBooking(HotelBooking booking) throws ResortBookingException;

    Optional<HotelBooking> getBookingById(Long id) throws ResortBookingException;

    List<HotelBooking> getAllBookings() throws ResortBookingException;

    List<HotelBooking> getBookingsByUser(User user) throws ResortBookingException;

    List<HotelBooking> getBookingsByHotel(Hotel hotel) throws ResortBookingException;

    List<HotelBooking> getBookingsByBookingStatus(String status) throws ResortBookingException;

    List<HotelBooking> getBookingsByPaymentStatus(String paymentStatus) throws ResortBookingException;

    List<HotelBooking> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate) throws ResortBookingException;

    List<HotelBooking> getBookingsByUserId(Long userId) throws ResortBookingException;

    List<HotelBooking> getBookingsByHotelId(Long hotelId) throws ResortBookingException;

    List<HotelBooking> getBookingsByStatusAndHotelId(String bookingStatus, Long hotelId) throws ResortBookingException;

    void deleteBooking(Long id) throws ResortBookingException;
}
