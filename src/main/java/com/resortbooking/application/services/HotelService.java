package com.resortbooking.application.services;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.exception.ResortBookingException;

import java.util.List;

public interface HotelService {

    String createHotel(HotelDto hotelDto) throws ResortBookingException;

    List<HotelDto> getAllHotels() throws ResortBookingException;

    HotelDto getHotelById(Long id) throws ResortBookingException;

	String updateHotel(Long id, HotelDto hotelDto);
}
