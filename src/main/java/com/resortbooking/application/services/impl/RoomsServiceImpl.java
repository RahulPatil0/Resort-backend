package com.resortbooking.application.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.AmenityRepository;
import com.resortbooking.application.dao.HotelRepository;
import com.resortbooking.application.dao.RoomsRepository;
import com.resortbooking.application.dto.RoomsDto;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.RoomsMapper;
import com.resortbooking.application.models.Amenity;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Rooms;
import com.resortbooking.application.services.RoomsService;

@Service
public class RoomsServiceImpl implements RoomsService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomsServiceImpl.class);

    @Autowired
    private RoomsRepository roomsRepository;
    
    @Autowired
    private HotelRepository hotelRepository;
    
    @Autowired
    private AmenityRepository amenityRepository;

    @Override
    public RoomsDto createRoom(RoomsDto dto, Long hotelId) throws ResortBookingException{
        try {
        	Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        	if(hotel.isEmpty()) {
        		throw new ResortBookingException("Hotel not found");
        	}
        	Rooms room = RoomsMapper.toEntity(dto, hotel.get());
        	room.setIsAvailable(true);
        	room.setCreatedDate(LocalDateTime.now());
        	room.setUpdatedAt(LocalDateTime.now());
        	
        	room = roomsRepository.save(room);
        	
			Amenity amenity = new Amenity();
			amenity.setAmenities(dto.getAmenity());
			amenity.setRooms(room);
			amenityRepository.save(amenity);
        	
			logger.info("Room created successfully with ID {}", room.getId());
            return dto;
        } catch (Exception e) {
            System.err.println("Error creating room: " + e.getMessage());
            throw new ResortBookingException("Error creating room: " + e.getMessage());
        }
    }

    @Override
    public List<Rooms> getAllRooms() throws ResortBookingException{
        try {
            return roomsRepository.findAll();
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching all rooms: " + e.getMessage());
            throw new ResortBookingException("Error fetching all rooms: " + e.getMessage());
        }
    }

    @Override
    public Optional<Rooms> getRoomById(Long id) throws ResortBookingException{
        try {
            return roomsRepository.findById(id);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching room by ID: " + e.getMessage());
            throw new ResortBookingException("Error fetching room by ID: " + e.getMessage());
        }
    }

    @Override
    public Rooms updateRoom(Rooms room) throws ResortBookingException{
        try {
            return roomsRepository.save(room);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error updating room: " + e.getMessage());
            throw new ResortBookingException("Error updating room: " + e.getMessage());
        }
    }

    @Override
    public void deleteRoom(Long id) throws ResortBookingException{
        try {
            roomsRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error deleting room: " + e.getMessage());
            throw new ResortBookingException("Error deleting room: " + e.getMessage());
        }
    }

    @Override
    public List<Rooms> getAvailableRooms() throws ResortBookingException{
        try {
            return roomsRepository.findByIsAvailableTrue();
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching available rooms: " + e.getMessage());
            throw new ResortBookingException("Error fetching available rooms: " + e.getMessage());
        }
    }

    @Override
    public List<Rooms> getRoomsByHotel(Hotel hotel) throws ResortBookingException{
        try {
            return roomsRepository.findByHotel(hotel);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching rooms by hotel: " + e.getMessage());
            throw new ResortBookingException("Error fetching rooms by hotel: " + e.getMessage());
        }
    }

    @Override
    public List<Rooms> getAvailableRoomsByHotel(Hotel hotel) throws ResortBookingException{
        try {
            return roomsRepository.findByHotelAndIsAvailableTrue(hotel);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching available rooms by hotel: " + e.getMessage());
            throw new ResortBookingException("Error fetching available rooms by hotel: " + e.getMessage());
        }
    }

    @Override
    public List<Rooms> getRoomsByType(Hotel hotel, String type) throws ResortBookingException{
        try {
            return roomsRepository.findByHotelAndTypeIgnoreCase(hotel, type);
        } catch (Exception e) {
            // Log the exception
//            System.err.println("Error fetching rooms by type: " + e.getMessage());
            throw new ResortBookingException("Error fetching rooms by type: " + e.getMessage());
        }
    }
}
