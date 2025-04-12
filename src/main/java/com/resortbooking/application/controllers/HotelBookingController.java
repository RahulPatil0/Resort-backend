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
import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.User;
import com.resortbooking.application.services.HotelBookingService;
import com.resortbooking.application.services.HotelService;
import com.resortbooking.application.services.UserService;

@RestController
@RequestMapping("/api/bookings")
public class HotelBookingController {

    private final HotelBookingService hotelBookingService;
    private final UserService userService;
    private final HotelService hotelService;

    @Autowired
    public HotelBookingController(HotelBookingService hotelBookingService,
                                  UserService userService,
                                  HotelService hotelService) {
        this.hotelBookingService = hotelBookingService;
        this.userService = userService;
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<HotelBooking> createBooking(@RequestBody HotelBooking booking) {
        try {
            booking.setCreatedAt(LocalDate.now().atStartOfDay());
            booking.setUpdatedAt(LocalDate.now().atStartOfDay());
            HotelBooking savedBooking = hotelBookingService.saveBooking(booking);
            return ResponseEntity.ok(savedBooking);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelBooking> getBookingById(@PathVariable Long id) {
        try {
            Optional<HotelBooking> booking = hotelBookingService.getBookingById(id);
            return booking.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<HotelBooking>> getAllBookings() {
        try {
            return ResponseEntity.ok(hotelBookingService.getAllBookings());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HotelBooking>> getBookingsByUser(@PathVariable Long userId) {
        try {
            Optional<User> user = userService.getUserById(userId);
            return user.map(u -> ResponseEntity.ok(hotelBookingService.getBookingsByUser(u)))
                       .orElse(ResponseEntity.notFound().build());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<?> getBookingsByHotel(@PathVariable Long hotelId) {
        try {
            HotelDto hotel = hotelService.getHotelById(hotelId); // assumes optional unwrap happens in service
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/status")
    public ResponseEntity<List<HotelBooking>> getBookingsByStatus(@RequestParam String bookingStatus) {
        try {
            return ResponseEntity.ok(hotelBookingService.getBookingsByBookingStatus(bookingStatus));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/payment-status")
    public ResponseEntity<List<HotelBooking>> getBookingsByPaymentStatus(@RequestParam String paymentStatus) {
        try {
            return ResponseEntity.ok(hotelBookingService.getBookingsByPaymentStatus(paymentStatus));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<HotelBooking>> getBookingsByDateRange(@RequestParam LocalDate start,
                                                                      @RequestParam LocalDate end) {
        try {
            return ResponseEntity.ok(hotelBookingService.getBookingsBetweenDates(start, end));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/hotel-status")
    public ResponseEntity<List<HotelBooking>> getBookingsByStatusAndHotel(@RequestParam String bookingStatus,
                                                                           @RequestParam Long hotelId) {
        try {
            return ResponseEntity.ok(hotelBookingService.getBookingsByStatusAndHotelId(bookingStatus, hotelId));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            Optional<HotelBooking> existing = hotelBookingService.getBookingById(id);
            if (existing.isPresent()) {
                hotelBookingService.deleteBooking(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelBooking> updateBooking(@PathVariable Long id, @RequestBody HotelBooking updatedBooking) {
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

                return ResponseEntity.ok(hotelBookingService.saveBooking(existingBooking));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
