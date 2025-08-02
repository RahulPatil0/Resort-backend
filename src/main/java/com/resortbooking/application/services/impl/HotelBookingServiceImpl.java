package com.resortbooking.application.services.impl;

import com.resortbooking.application.dao.HotelBookingRepository;
import com.resortbooking.application.dao.HotelRepository;
import com.resortbooking.application.dao.UserRepository;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.User;
import com.resortbooking.application.services.HotelBookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HotelBookingServiceImpl implements HotelBookingService {

    @Autowired
    private HotelBookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelBooking saveBooking(HotelBooking booking) throws ResortBookingException {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<HotelBooking> getBookingById(Long id) throws ResortBookingException {
        return bookingRepository.findById(id);
    }

    @Override
    public List<HotelBooking> getAllBookings() throws ResortBookingException {
        return bookingRepository.findAll();
    }

    @Override
    public List<HotelBooking> getBookingsByUser(User user) throws ResortBookingException {
        return bookingRepository.findByUser(user);
    }

    @Override
    public List<HotelBooking> getBookingsByHotel(Hotel hotel) throws ResortBookingException {
        return bookingRepository.findByHotel(hotel);
    }

    @Override
    public List<HotelBooking> getBookingsByBookingStatus(String status) throws ResortBookingException {
        return bookingRepository.findByBookingStatus(status);
    }

    @Override
    public List<HotelBooking> getBookingsByPaymentStatus(String paymentStatus) throws ResortBookingException {
        return bookingRepository.findByPaymentStatus(paymentStatus);
    }

    @Override
    public List<HotelBooking> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate) throws ResortBookingException {
        return bookingRepository.findByCheckInDateBetween(startDate, endDate);
    }

    @Override
    public List<HotelBooking> getBookingsByUserId(Long userId) throws ResortBookingException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResortBookingException("User not found"));
        return bookingRepository.findByUser(user);
    }

    @Override
    public List<HotelBooking> getBookingsByHotelId(Long hotelId) throws ResortBookingException {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResortBookingException("Hotel not found"));
        return bookingRepository.findByHotel(hotel);
    }

    @Override
    public List<HotelBooking> getBookingsByStatusAndHotelId(String bookingStatus, Long hotelId) throws ResortBookingException {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResortBookingException("Hotel not found"));
        return bookingRepository.findByBookingStatusAndHotelId(bookingStatus, hotel);
    }

    @Override
    public void deleteBooking(Long id) throws ResortBookingException {
        if (!bookingRepository.existsById(id)) {
            throw new ResortBookingException("Booking not found");
        }
        bookingRepository.deleteById(id);
    }
}
