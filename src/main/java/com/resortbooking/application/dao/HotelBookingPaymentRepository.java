package com.resortbooking.application.dao;

import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.HotelBookingPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelBookingPaymentRepository extends JpaRepository<HotelBookingPayment, Long> {

//    List<HotelBookingPayment> findByBooking(HotelBooking booking);
//
//    List<HotelBookingPayment> findByPaymentStatus(String paymentStatus);

//    HotelBookingPayment findByTransactionId(String transactionId);
}
