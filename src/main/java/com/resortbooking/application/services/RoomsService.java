package com.resortbooking.application.services;

import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Rooms;
import java.util.List;
import java.util.Optional;

public interface RoomsService {
	Rooms createRoom(Rooms room) throws ResortBookingException;

	List<Rooms> getAllRooms() throws ResortBookingException;

	Optional<Rooms> getRoomById(Long id) throws ResortBookingException;

	Rooms updateRoom(Rooms room) throws ResortBookingException;

	void deleteRoom(Long id) throws ResortBookingException;

	List<Rooms> getAvailableRooms() throws ResortBookingException;

	List<Rooms> getRoomsByHotel(Hotel hotel) throws ResortBookingException;

	List<Rooms> getAvailableRoomsByHotel(Hotel hotel) throws ResortBookingException;

	List<Rooms> getRoomsByType(Hotel hotel, String type) throws ResortBookingException;
}
