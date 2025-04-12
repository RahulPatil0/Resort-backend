package com.resortbooking.application.controllers;

import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.HotelBookingPayment;
import com.resortbooking.application.services.HotelBookingPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class HotelBookingPaymentController {

    private static final Logger logger = LoggerFactory.getLogger(HotelBookingPaymentController.class);

    @Autowired
    private HotelBookingPaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody HotelBookingPayment payment) {
        try {
            HotelBookingPayment saved = paymentService.savePayment(payment);
            logger.info("Payment saved successfully with ID {}", saved.getId());
            return ResponseEntity.ok(saved);
        } catch (Exception ex) {
            logger.error("Error while saving payment: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("An error occurred while saving the payment.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        try {
            Optional<HotelBookingPayment> payment = paymentService.getPaymentById(id);
            return payment.map(ResponseEntity::ok)
                          .orElseGet(() -> {
                              logger.warn("Payment with ID {} not found", id);
                              return ResponseEntity.notFound().build();
                          });
        } catch (Exception ex) {
            logger.error("Error while fetching payment by ID {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("An error occurred while fetching the payment.");
        }
    }

    @GetMapping("/transaction/{txnId}")
    public ResponseEntity<?> getPaymentByTransactionId(@PathVariable String txnId) {
        try {
            Optional<HotelBookingPayment> payment = paymentService.getPaymentByTransactionId(txnId);
            return payment.map(ResponseEntity::ok)
                          .orElseGet(() -> {
                              logger.warn("Payment with transaction ID {} not found", txnId);
                              return ResponseEntity.notFound().build();
                          });
        } catch (Exception ex) {
            logger.error("Error while fetching payment by transaction ID {}: {}", txnId, ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("An error occurred while fetching the payment.");
        }
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getPaymentsByBooking(@PathVariable Long bookingId) {
        try {
            HotelBooking booking = new HotelBooking();
            booking.setId(bookingId);
            List<HotelBookingPayment> payments = paymentService.getPaymentsByBooking(booking);
            logger.info("Found {} payments for booking ID {}", payments.size(), bookingId);
            return ResponseEntity.ok(payments);
        } catch (Exception ex) {
            logger.error("Error while fetching payments by booking ID {}: {}", bookingId, ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("An error occurred while fetching the payments.");
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getPaymentsByStatus(@PathVariable String status) {
        try {
            List<HotelBookingPayment> payments = paymentService.getPaymentsByStatus(status.toUpperCase());
            logger.info("Found {} payments with status {}", payments.size(), status);
            return ResponseEntity.ok(payments);
        } catch (Exception ex) {
            logger.error("Error while fetching payments by status {}: {}", status, ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("An error occurred while fetching the payments.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        try {
            Optional<HotelBookingPayment> existing = paymentService.getPaymentById(id);
            if (existing.isPresent()) {
                paymentService.deletePayment(id);
                logger.info("Deleted payment with ID {}", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Payment with ID {} not found for deletion", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            logger.error("Error while deleting payment with ID {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("An error occurred while deleting the payment.");
        }
    }
}
