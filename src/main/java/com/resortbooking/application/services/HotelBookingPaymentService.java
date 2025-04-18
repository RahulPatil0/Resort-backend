package com.resortbooking.application.services;

import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.HotelBookingPayment;

import java.util.List;
import java.util.Optional;

public interface HotelBookingPaymentService {

    HotelBookingPayment savePayment(HotelBookingPayment payment) throws ResortBookingException;

    Optional<HotelBookingPayment> getPaymentById(Long id) throws ResortBookingException;

    Optional<HotelBookingPayment> getPaymentByTransactionId(String transactionId) throws ResortBookingException;

    List<HotelBookingPayment> getPaymentsByBooking(HotelBooking booking) throws ResortBookingException;

    List<HotelBookingPayment> getPaymentsByStatus(String status) throws ResortBookingException;

    void deletePayment(Long id) throws ResortBookingException;
}
