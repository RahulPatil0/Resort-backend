package com.resortbooking.application.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.MediaRepository;
import com.resortbooking.application.dao.AmenityRepository;
import com.resortbooking.application.dao.HotelRepository;
import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.dto.MediaDto;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.HotelMapper;
import com.resortbooking.application.mappers.MediaMapper;
import com.resortbooking.application.models.Amenity;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Media;
import com.resortbooking.application.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private AmenityRepository amenityRepository;

	@Override
	public String createHotel(HotelDto hotelDto) throws ResortBookingException {
		try {
			Hotel hotel = HotelMapper.toEntity(hotelDto);

			if (hotelDto.getMedia().isEmpty()) {
				throw new ResortBookingException("Photos are mandatory for registration.");
			}			
			hotel = hotelRepository.save(hotel);

			for (MediaDto mediaDto : hotelDto.getMedia()) {
				Media media = MediaMapper.toEntity(mediaDto);

				media.setHotel(hotel);
				media.setUploadedAt(LocalDateTime.now());
				mediaRepository.save(media);
			}

			Amenity amenity = new Amenity();
			amenity.setAmenities(hotelDto.getAmenity());
			amenity.setHotel(hotel);
			amenityRepository.save(amenity);

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
