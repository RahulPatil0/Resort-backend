package com.resortbooking.application.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.User;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.HotelBookingService;
import com.resortbooking.application.services.HotelService;
import com.resortbooking.application.services.UserService;

@RestController
@RequestMapping("/api/bookings")
public class HotelBookingController {

  
    @Autowired
    private HotelBookingService hotelBookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResortBookingResponse createBooking(@RequestBody HotelBooking booking) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";

        try {
            booking.setCreatedAt(LocalDate.now().atStartOfDay());
            booking.setUpdatedAt(LocalDate.now().atStartOfDay());
            HotelBooking savedBooking = hotelBookingService.saveBooking(booking);
            status = HttpStatus.CREATED;
            return new ResortBookingResponse(savedBooking, status);
        } catch (ResortBookingException e) {
            message = e.getMessage();
        } catch (Exception e) {
            message = "Error creating booking: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

    @GetMapping("/{id}")
    public ResortBookingResponse getBookingById(@PathVariable Long id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Booking not found with ID: " + id;

        try {
            Optional<HotelBooking> booking = hotelBookingService.getBookingById(id);
            if (booking.isPresent()) {
                return new ResortBookingResponse(booking.get(), HttpStatus.OK);
            }
        } catch (ResortBookingException e) {
            message = e.getMessage();
        }catch (Exception e) {
            message = "Error retrieving booking: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

    @GetMapping
    public ResortBookingResponse getAllBookings() {
        try {
            List<HotelBooking> bookings = hotelBookingService.getAllBookings();
            return new ResortBookingResponse(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResortBookingResponse("Error retrieving bookings: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResortBookingResponse getBookingsByUser(@PathVariable Long userId) {
        try {
            Optional<User> user = userService.getUserById(userId);
            if (user.isPresent()) {
                List<HotelBooking> bookings = hotelBookingService.getBookingsByUser(user.get());
                return new ResortBookingResponse(bookings, HttpStatus.OK);
            }
            return new ResortBookingResponse("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResortBookingResponse("Error retrieving user bookings: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hotel/{hotelId}")
    public ResortBookingResponse getBookingsByHotel(@PathVariable Long hotelId) {
        try {
            HotelDto hotel = hotelService.getHotelById(hotelId);
            return new ResortBookingResponse(hotel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResortBookingResponse("Error retrieving hotel bookings: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status")
    public ResortBookingResponse getBookingsByStatus(@RequestParam String bookingStatus) {
        try {
            List<HotelBooking> bookings = hotelBookingService.getBookingsByBookingStatus(bookingStatus);
            return new ResortBookingResponse(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResortBookingResponse("Error retrieving bookings by status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/payment-status")
    public ResortBookingResponse getBookingsByPaymentStatus(@RequestParam String paymentStatus) {
        try {
            List<HotelBooking> bookings = hotelBookingService.getBookingsByPaymentStatus(paymentStatus);
            return new ResortBookingResponse(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResortBookingResponse("Error retrieving bookings by payment status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/date-range")
    public ResortBookingResponse getBookingsByDateRange(@RequestParam LocalDate start,
                                                         @RequestParam LocalDate end) {
        try {
            List<HotelBooking> bookings = hotelBookingService.getBookingsBetweenDates(start, end);
            return new ResortBookingResponse(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResortBookingResponse("Error retrieving bookings by date range: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hotel-status")
    public ResortBookingResponse getBookingsByStatusAndHotel(@RequestParam String bookingStatus,
                                                              @RequestParam Long hotelId) {
        try {
            List<HotelBooking> bookings = hotelBookingService.getBookingsByStatusAndHotelId(bookingStatus, hotelId);
            return new ResortBookingResponse(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResortBookingResponse("Error retrieving bookings by status and hotel: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResortBookingResponse deleteBooking(@PathVariable Long id) {
        try {
            Optional<HotelBooking> existing = hotelBookingService.getBookingById(id);
            if (existing.isPresent()) {
                hotelBookingService.deleteBooking(id);
                return new ResortBookingResponse("Booking deleted successfully", HttpStatus.OK);
            } else {
                return new ResortBookingResponse("Booking not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResortBookingResponse("Error deleting booking: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResortBookingResponse updateBooking(@PathVariable Long id, @RequestBody HotelBooking updatedBooking) {
        try {
            Optional<HotelBooking> existing = hotelBookingService.getBookingById(id);
            if (existing.isPresent()) {
                HotelBooking existingBooking = existing.get();

                existingBooking.setCheckInDate(updatedBooking.getCheckInDate());
                existingBooking.setCheckOutDate(updatedBooking.getCheckOutDate());
                existingBooking.setNumberOfGuests(updatedBooking.getNumberOfGuests());
                existingBooking.setTotalAmount(updatedBooking.getTotalAmount());
                existingBooking.setPaidAmount(updatedBooking.getPaidAmount());
                existingBooking.setPaymentStatus(updatedBooking.getPaymentStatus());
                existingBooking.setBookingStatus(updatedBooking.getBookingStatus());
                existingBooking.setUpdatedAt(LocalDate.now().atStartOfDay());

                HotelBooking updated = hotelBookingService.saveBooking(existingBooking);
                return new ResortBookingResponse(updated, HttpStatus.OK);
            } else {
                return new ResortBookingResponse("Booking not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResortBookingResponse("Error updating booking: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
