package com.resortbooking.application.mappers;

import com.resortbooking.application.dto.HotelBookingDto;
import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.dto.UserDTO;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelBooking;
import com.resortbooking.application.models.User;

import java.math.BigDecimal;

public class HotelBookingMapper {

    public static HotelBookingDto toDto(HotelBooking booking) {
        if (booking == null) return null;

        HotelDto hotelDTO = HotelMapper.toDto(booking.getHotel());
        UserDTO userDTO = UserMapper.toDto(booking.getUser());

        return new HotelBookingDto(
                booking.getId(),
                userDTO,
                hotelDTO,
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getNumberOfGuests(),
                BigDecimal.valueOf(booking.getTotalAmount()),
                booking.getBookingStatus(), // or paymentStatus if needed
                booking.getCreatedAt()
        );
    }

    public static HotelBooking toEntity(HotelBookingDto dto) {
        if (dto == null) return null;

        Hotel hotel = HotelMapper.toEntity(dto.getHotel());
        User user = UserMapper.toEntity(dto.getUser());

        HotelBooking booking = new HotelBooking();
        booking.setId(dto.getId());
        booking.setUser(user);
        booking.setHotel(hotel);
        booking.setCheckInDate(dto.getCheckInDate());
        booking.setCheckOutDate(dto.getCheckOutDate());
        booking.setNumberOfGuests(dto.getNumberOfGuests());
        booking.setTotalAmount(dto.getTotalAmount() != null ? dto.getTotalAmount().doubleValue() : 0.0);
        booking.setBookingStatus(dto.getStatus());
        booking.setCreatedAt(dto.getCreatedAt());

        return booking;
    }
}
