package com.resortbooking.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortbooking.application.models.HotelBookingPayment;

@Repository
public interface HotelBookingPaymentRepository extends JpaRepository<HotelBookingPayment, Long> {

//    List<HotelBookingPayment> findByBooking(HotelBooking booking);
//
//    List<HotelBookingPayment> findByPaymentStatus(String paymentStatus);

//    HotelBookingPayment findByTransactionId(String transactionId);
}
