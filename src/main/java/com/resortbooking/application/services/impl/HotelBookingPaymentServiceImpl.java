package com.resortbooking.application.services.impl;

import com.resortbooking.application.dao.HotelBookingPaymentRepository;
import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.HotelBookingPayment;
import com.resortbooking.application.services.HotelBookingPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelBookingPaymentServiceImpl implements HotelBookingPaymentService {

    @Autowired
    private HotelBookingPaymentRepository paymentRepository;

    @Override
    public HotelBookingPayment savePayment(HotelBookingPayment payment) {
        try {
            return paymentRepository.save(payment);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error saving payment: " + e.getMessage());
            throw new RuntimeException("Error saving payment: " + e.getMessage());
        }
    }

    @Override
    public Optional<HotelBookingPayment> getPaymentById(Long id) {
        try {
            return paymentRepository.findById(id);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching payment by ID: " + e.getMessage());
            throw new RuntimeException("Error fetching payment by ID: " + e.getMessage());
        }
    }

    @Override
    public Optional<HotelBookingPayment> getPaymentByTransactionId(String transactionId) {
        try {
            return Optional.ofNullable(paymentRepository.findByTransactionId(transactionId));
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching payment by transaction ID: " + e.getMessage());
            throw new RuntimeException("Error fetching payment by transaction ID: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBookingPayment> getPaymentsByBooking(HotelBooking booking) {
        try {
            return paymentRepository.findByBooking(booking);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching payments by booking: " + e.getMessage());
            throw new RuntimeException("Error fetching payments by booking: " + e.getMessage());
        }
    }

    @Override
    public List<HotelBookingPayment> getPaymentsByStatus(String status) {
        try {
            return paymentRepository.findByPaymentStatus(status);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching payments by status: " + e.getMessage());
            throw new RuntimeException("Error fetching payments by status: " + e.getMessage());
        }
    }

    @Override
    public void deletePayment(Long id) {
        try {
            paymentRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error deleting payment: " + e.getMessage());
            throw new RuntimeException("Error deleting payment: " + e.getMessage());
        }
    }
}
