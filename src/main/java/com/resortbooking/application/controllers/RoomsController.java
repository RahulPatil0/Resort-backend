package com.resortbooking.application.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.dto.RoomsDto;
import com.resortbooking.application.mappers.HotelMapper;
import com.resortbooking.application.mappers.RoomsMapper;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Rooms;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.HotelService;
import com.resortbooking.application.services.RoomsService;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomsController {

	@Autowired
	private RoomsService roomsService;

	@Autowired
	private HotelService hotelService;

	@PostMapping
	public ResortBookingResponse<RoomsDto> createRoom(@RequestBody RoomsDto dto,
			@RequestParam Long hotelId) {
		try {
			dto=roomsService.createRoom(dto,hotelId);
			return new ResortBookingResponse("Hotel created", HttpStatus.CREATED,dto);

		} catch (Exception e) {
			return new ResponseEntity<>(new ResortBookingResponse<>("Error creating room: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResortBookingResponse<RoomsDto>> getRoomById(@PathVariable Long id) {
		try {
			Optional<Rooms> roomOpt = roomsService.getRoomById(id);
			if (roomOpt.isPresent()) {
				RoomsDto dto = RoomsMapper.toDto(roomOpt.get());
				return new ResponseEntity<>(
						new ResortBookingResponse<>("Room retrieved successfully.", HttpStatus.OK, dto), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new ResortBookingResponse<>("Room not found with ID: " + id, HttpStatus.NOT_FOUND),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new ResortBookingResponse<>("Error retrieving room: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/available")
	public ResponseEntity<ResortBookingResponse<List<RoomsDto>>> getAvailableRooms() {
		try {
			List<Rooms> rooms = roomsService.getAvailableRooms();
			List<RoomsDto> roomDtos = rooms.stream().map(RoomsMapper::toDto).collect(Collectors.toList());

			if (roomDtos.isEmpty()) {
				return new ResponseEntity<>(
						new ResortBookingResponse<>("No available rooms found.", HttpStatus.NOT_FOUND),
						HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(
						new ResortBookingResponse<>("Available rooms retrieved successfully.", HttpStatus.OK, roomDtos),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new ResortBookingResponse<>("Error fetching available rooms: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<ResortBookingResponse<List<RoomsDto>>> getRoomsByHotel(@PathVariable Long hotelId) {
		try {
			HotelDto hotelDto = hotelService.getHotelById(hotelId);
			if (hotelDto == null) {
				return new ResponseEntity<>(new ResortBookingResponse<>("Hotel not found.", HttpStatus.NOT_FOUND),
						HttpStatus.NOT_FOUND);
			}

			Hotel hotel = HotelMapper.toEntity(hotelDto);
			List<Rooms> rooms = roomsService.getRoomsByHotel(hotel);
			List<RoomsDto> roomDtos = rooms.stream().map(RoomsMapper::toDto).collect(Collectors.toList());

			String message = roomDtos.isEmpty() ? "No rooms found for this hotel." : "Rooms retrieved successfully.";
			return new ResponseEntity<>(new ResortBookingResponse<>(message, HttpStatus.OK, roomDtos), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResortBookingResponse<>("Error retrieving rooms by hotel: " + e.getMessage(),
							HttpStatus.INTERNAL_SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResortBookingResponse<RoomsDto>> updateRoom(@PathVariable Long id,
			@RequestBody Rooms roomDetails) {
		try {
			Optional<Rooms> existingOpt = roomsService.getRoomById(id);
			if (existingOpt.isPresent()) {
				Rooms room = existingOpt.get();
				room.setRoomNumber(roomDetails.getRoomNumber());
				room.setType(roomDetails.getType());
				room.setPricePerNight(roomDetails.getPricePerNight());
				room.setCapacity(roomDetails.getCapacity());
				room.setIsAvailable(roomDetails.getIsAvailable());
				room.setDescription(roomDetails.getDescription());

				Rooms updatedRoom = roomsService.updateRoom(room);
				RoomsDto dto = RoomsMapper.toDto(updatedRoom);

				return new ResponseEntity<>(
						new ResortBookingResponse<>("Room updated successfully.", HttpStatus.OK, dto), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ResortBookingResponse<>("Room not found.", HttpStatus.NOT_FOUND),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new ResortBookingResponse<>("Error updating room: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResortBookingResponse<Void>> deleteRoom(@PathVariable Long id) {
		try {
			Optional<Rooms> room = roomsService.getRoomById(id);
			if (room.isPresent()) {
				roomsService.deleteRoom(id);
				return new ResponseEntity<>(
						new ResortBookingResponse<>("Room deleted successfully.", HttpStatus.NO_CONTENT),
						HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(new ResortBookingResponse<>("Room not found.", HttpStatus.NOT_FOUND),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new ResortBookingResponse<>("Error deleting room: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
