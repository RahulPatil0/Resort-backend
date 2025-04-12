package com.resortbooking.application.services;

import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.HotelBookingPayment;

import java.util.List;
import java.util.Optional;

public interface HotelBookingPaymentService {

    HotelBookingPayment savePayment(HotelBookingPayment payment);

    Optional<HotelBookingPayment> getPaymentById(Long id);

    Optional<HotelBookingPayment> getPaymentByTransactionId(String transactionId);

    List<HotelBookingPayment> getPaymentsByBooking(HotelBooking booking);

    List<HotelBookingPayment> getPaymentsByStatus(String status);

    void deletePayment(Long id);
}
