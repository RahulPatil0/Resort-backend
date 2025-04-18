package com.resortbooking.application.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.HotelBookingPayment;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.HotelBookingPaymentService;

@RestController
@RequestMapping("/api/payments")
public class HotelBookingPaymentController {

	private static final Logger logger = LoggerFactory.getLogger(HotelBookingPaymentController.class);

	@Autowired
	private HotelBookingPaymentService paymentService;

	@PostMapping
	public ResortBookingResponse createPayment(@RequestBody HotelBookingPayment payment) {
		try {
			HotelBookingPayment saved = paymentService.savePayment(payment);
			logger.info("Payment saved successfully with ID {}", saved.getId());
			return new ResortBookingResponse(saved, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error while saving payment: {}", ex.getMessage(), ex);
			return new ResortBookingResponse("An error occurred while saving the payment.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResortBookingResponse getPaymentById(@PathVariable Long id) {
		try {
			Optional<HotelBookingPayment> payment = paymentService.getPaymentById(id);
			if (payment.isPresent()) {
				return new ResortBookingResponse(payment.get(), HttpStatus.OK);
			} else {
				logger.warn("Payment with ID {} not found", id);
				return new ResortBookingResponse("Payment not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.error("Error while fetching payment by ID {}: {}", id, ex.getMessage(), ex);
			return new ResortBookingResponse("An error occurred while fetching the payment.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/transaction/{txnId}")
	public ResortBookingResponse getPaymentByTransactionId(@PathVariable String txnId) {
		try {
			Optional<HotelBookingPayment> payment = paymentService.getPaymentByTransactionId(txnId);
			if (payment.isPresent()) {
				return new ResortBookingResponse(payment.get(), HttpStatus.OK);
			} else {
				logger.warn("Payment with transaction ID {} not found", txnId);
				return new ResortBookingResponse("Payment not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.error("Error while fetching payment by transaction ID {}: {}", txnId, ex.getMessage(), ex);
			return new ResortBookingResponse("An error occurred while fetching the payment.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/booking/{bookingId}")
	public ResortBookingResponse getPaymentsByBooking(@PathVariable Long bookingId) {
		try {
			HotelBooking booking = new HotelBooking();
			booking.setId(bookingId);
			List<HotelBookingPayment> payments = paymentService.getPaymentsByBooking(booking);
			logger.info("Found {} payments for booking ID {}", payments.size(), bookingId);
			return new ResortBookingResponse(payments, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error while fetching payments by booking ID {}: {}", bookingId, ex.getMessage(), ex);
			return new ResortBookingResponse("An error occurred while fetching the payments.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/status/{status}")
	public ResortBookingResponse getPaymentsByStatus(@PathVariable String status) {
		try {
			List<HotelBookingPayment> payments = paymentService.getPaymentsByStatus(status.toUpperCase());
			logger.info("Found {} payments with status {}", payments.size(), status);
			return new ResortBookingResponse(payments, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error while fetching payments by status {}: {}", status, ex.getMessage(), ex);
			return new ResortBookingResponse("An error occurred while fetching the payments.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResortBookingResponse deletePayment(@PathVariable Long id) {
		try {
			Optional<HotelBookingPayment> existing = paymentService.getPaymentById(id);
			if (existing.isPresent()) {
				paymentService.deletePayment(id);
				logger.info("Deleted payment with ID {}", id);
				return new ResortBookingResponse("Payment deleted successfully", HttpStatus.NO_CONTENT);
			} else {
				logger.warn("Payment with ID {} not found for deletion", id);
				return new ResortBookingResponse("Payment not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.error("Error while deleting payment with ID {}: {}", id, ex.getMessage(), ex);
			return new ResortBookingResponse("An error occurred while deleting the payment.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
