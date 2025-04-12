package com.resortbooking.application.mappers;

import com.resortbooking.application.dto.HotelBookingPaymentDTO;
import com.resortbooking.application.dto.HotelBookingDto;
import com.resortbooking.application.dto.UserDTO;
import com.resortbooking.application.models.HotelBookingPayment;

import java.math.BigDecimal;

public class HotelBookingPaymentMapper {

    public static HotelBookingPaymentDTO toDTO(HotelBookingPayment payment, UserDTO userDto, HotelBookingDto bookingDto) {
        if (payment == null) {
            return null;
        }

        HotelBookingPaymentDTO dto = new HotelBookingPaymentDTO();
        dto.setId(payment.getId());
        dto.setUser(userDto); // You can map User entity to UserDTO elsewhere
        dto.setBooking(bookingDto); // You can map HotelBooking entity to HotelBookingDto elsewhere
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setTransactionId(payment.getTransactionId());
        dto.setAmount(BigDecimal.valueOf(payment.getAmount()));
        dto.setStatus(payment.getPaymentStatus());
        dto.setPaidAt(payment.getPaymentDate());

        return dto;
    }

    public static HotelBookingPayment toEntity(HotelBookingPaymentDTO dto) {
        if (dto == null) {
            return null;
        }

        HotelBookingPayment entity = new HotelBookingPayment();
        entity.setId(dto.getId());
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setTransactionId(dto.getTransactionId());
        entity.setAmount(dto.getAmount().doubleValue());
        entity.setPaymentStatus(dto.getStatus());
        entity.setPaymentDate(dto.getPaidAt());

        // Note: You'll need to manually set `booking` from the dto.getBooking() if required
        // entity.setBooking(...); 

        return entity;
    }
}
