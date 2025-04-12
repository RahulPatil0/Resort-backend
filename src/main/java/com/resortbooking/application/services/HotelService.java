package com.resortbooking.application.services;

import com.resortbooking.application.dto.HotelDto;

import java.util.List;

public interface HotelService {

    HotelDto createHotel(HotelDto hotelDto);

    List<HotelDto> getAllHotels();

    HotelDto getHotelById(Long id);
}
