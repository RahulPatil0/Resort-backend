package com.resortbooking.application.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.HotelBookingRepository;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.User;
import com.resortbooking.application.services.HotelBookingService;

@Service
public class HotelBookingServiceImpl implements HotelBookingService {

    @Autowired
    private HotelBookingRepository bookingRepository;
    
    @Override
    public HotelBooking saveBooking(HotelBooking booking) throws ResortBookingException {
        try {
            return bookingRepository.save(booking);
        } catch (Exception e) {
            // Log the exception (use a logger in production)
//            System.err.println("Error saving booking: " + e.getMessage());
            throw new ResortBookingException("Error saving booking: " + e.getMessage());
        }
    }

    @Override
    public Optional<HotelBooking> getBookingById(Long id) throws ResortBookingException {
        try {
            return bookingRepository.findById(id);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching booking by ID: " + e.getMessage());
            throw new ResortBookingException("Error fetching booking by ID: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBooking> getAllBookings() throws ResortBookingException {
        try {
            return bookingRepository.findAll();
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching all bookings: " + e.getMessage());
            throw new ResortBookingException("Error fetching all bookings: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBooking> getBookingsByUser(User user) throws ResortBookingException {
        try {
            return bookingRepository.findByUser(user);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching bookings by user: " + e.getMessage());
            throw new ResortBookingException("Error fetching bookings by user: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBooking> getBookingsByHotel(Hotel hotel) throws ResortBookingException {
        try {
            return bookingRepository.findByHotel(hotel);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching bookings by hotel: " + e.getMessage());
            throw new ResortBookingException("Error fetching bookings by hotel: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBooking> getBookingsByBookingStatus(String status) throws ResortBookingException {
        try {
            return bookingRepository.findByBookingStatus(status);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching bookings by status: " + e.getMessage());
            throw new ResortBookingException("Error fetching bookings by status: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBooking> getBookingsByPaymentStatus(String paymentStatus) throws ResortBookingException {
        try {
            return bookingRepository.findByPaymentStatus(paymentStatus);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching bookings by payment status: " + e.getMessage());
            throw new ResortBookingException("Error fetching bookings by payment status: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBooking> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate) throws ResortBookingException {
        try {
            return bookingRepository.findByCheckInDateBetween(startDate, endDate);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching bookings between dates: " + e.getMessage());
            throw new ResortBookingException("Error fetching bookings between dates: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBooking> getBookingsByUserId(Long userId) throws ResortBookingException {
        try {
            return bookingRepository.findByUserId(userId);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching bookings by user ID: " + e.getMessage());
            throw new ResortBookingException("Error fetching bookings by user ID: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBooking> getBookingsByHotelId(Long hotelId) throws ResortBookingException {
        try {
            return bookingRepository.findByHotelId(hotelId);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching bookings by hotel ID: " + e.getMessage());
            throw new ResortBookingException("Error fetching bookings by hotel ID: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBooking> getBookingsByStatusAndHotelId(String bookingStatus, Long hotelId) throws ResortBookingException {
        try {
            return bookingRepository.findByBookingStatusAndHotelId(bookingStatus, hotelId);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching bookings by status and hotel ID: " + e.getMessage());
            throw new ResortBookingException("Error fetching bookings by status and hotel ID: " + e.getMessage());
        }
    }

    @Override
    public void deleteBooking(Long id) throws ResortBookingException {
        try {
            bookingRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error deleting booking: " + e.getMessage());
            throw new ResortBookingException("Error deleting booking: " + e.getMessage());
        }
    }
}
