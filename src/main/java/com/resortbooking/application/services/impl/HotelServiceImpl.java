package com.resortbooking.application.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.HotelRepository;
import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.mappers.HotelMapper;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        try {
            Hotel hotel = HotelMapper.toEntity(hotelDto);
            Hotel savedHotel = hotelRepository.save(hotel);
            return HotelMapper.toDto(savedHotel);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error creating hotel: " + e.getMessage());
            throw new RuntimeException("Error creating hotel: " + e.getMessage());
        }
    }

    @Override
    public List<HotelDto> getAllHotels() {
        try {
            return hotelRepository.findAll().stream()
                    .map(HotelMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching all hotels: " + e.getMessage());
            throw new RuntimeException("Error fetching all hotels: " + e.getMessage());
        }
    }

    @Override
    public HotelDto getHotelById(Long id) {
        try {
            return hotelRepository.findById(id)
                    .map(HotelMapper::toDto)
                    .orElse(null);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching hotel by ID: " + e.getMessage());
            throw new RuntimeException("Error fetching hotel by ID: " + e.getMessage());
        }
    }
}
