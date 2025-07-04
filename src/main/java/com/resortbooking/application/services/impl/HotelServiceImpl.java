package com.resortbooking.application.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.HotelPhotosRepository;
import com.resortbooking.application.dao.HotelRepository;
import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.dto.HotelPhotosDto;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.HotelMapper;
import com.resortbooking.application.mappers.HotelPhotosMapper;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;
import com.resortbooking.application.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private HotelPhotosRepository photosRepository;

	@Override
	public String createHotel(HotelDto hotelDto) throws ResortBookingException {
		try {
			Hotel hotel = HotelMapper.toEntity(hotelDto);
			
			if(!hotelDto.getHotelPhotos().isEmpty()) {
				
				hotel.setCreatedAt(LocalDateTime.now());
				hotel.setLastUpdatedAt(LocalDateTime.now());
				
				hotel = hotelRepository.save(hotel);
			
				for (HotelPhotosDto photoDto : hotelDto.getHotelPhotos()) {
				    HotelPhotos photo = HotelPhotosMapper.toEntity(photoDto);
				    
				    photo.setHotel(hotel);
				    photo.setUploadedAt(LocalDateTime.now());
				    photo = photosRepository.save(photo);
				}
				
			} else {
				throw new ResortBookingException("Photos are mandatory for registration.");
			}
			
			logger.info("Hotel created successfully with ID {}", hotel.getId());
			return hotel.getHotelName() + " registerd successfully";
		} catch (Exception e) {
			logger.error("Error creating hotel: " + e.getMessage());
			throw new ResortBookingException("Error creating hotel: " + e.getMessage());
		}
	}

	@Override
	public List<HotelDto> getAllHotels() throws ResortBookingException {
		try {
			return hotelRepository.findAll().stream().map(HotelMapper::toDto).collect(Collectors.toList());
		} catch (Exception e) {
			// Log the exception
//            System.err.println("Error fetching all hotels: " + e.getMessage());
			throw new ResortBookingException("Error fetching all hotels: " + e.getMessage());
		}
	}

	@Override
	public HotelDto getHotelById(Long id) throws ResortBookingException {
		try {
			return hotelRepository.findById(id).map(HotelMapper::toDto).orElse(null);
		} catch (Exception e) {
			// Log the exception
//            System.err.println("Error fetching hotel by ID: " + e.getMessage());
			throw new ResortBookingException("Error fetching hotel by ID: " + e.getMessage());
		}
	}

	@Override
	public String updateHotel(Long id, HotelDto hotelDto) {
		// TODO Auto-generated method stub
		return null;
	}
}
