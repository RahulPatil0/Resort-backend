
package com.resortbooking.application.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.mappers.HotelMapper;
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

    // Create Room
    @PostMapping
    public ResortBookingResponse createRoom(@RequestBody Rooms room) {
        String message = "";
        HttpStatus status = HttpStatus.OK;

        try {
            if (room.getHotel() == null || room.getHotel().getId() == null) {
                message = "Hotel ID must be provided.";
                status = HttpStatus.BAD_REQUEST;
            } else {
                HotelDto hotelDto = hotelService.getHotelById(room.getHotel().getId());
                if (hotelDto == null) {
                    message = "Hotel not found with given ID.";
                    status = HttpStatus.NOT_FOUND;
                } else {
                    Hotel hotel = HotelMapper.toEntity(hotelDto);
                    room.setHotel(hotel);
                    roomsService.createRoom(room);
                    message = "Room created successfully.";
                    status = HttpStatus.CREATED;
                }
            }
        } catch (Exception e) {
            message = "Error creating room: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

    // 🔹 Get Room by ID
    @GetMapping("/{id}")
    public ResortBookingResponse getRoomById(@PathVariable Long id) {
        String message = "";
        HttpStatus status = HttpStatus.OK;

        try {
            Optional<Rooms> room = roomsService.getRoomById(id);
            if (room.isPresent()) {
                message = "Room retrieved successfully.";
            } else {
                message = "Room not found with ID: " + id;
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            message = "Error retrieving room: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

    // 🔹 Get Available Rooms
    @GetMapping("/available")
    public ResortBookingResponse getAvailableRooms() {
        String message = "";
        HttpStatus status = HttpStatus.OK;

        try {
            List<Rooms> rooms = roomsService.getAvailableRooms();
            if (rooms.isEmpty()) {
                message = "No available rooms found.";
                status = HttpStatus.NOT_FOUND;
            } else {
                message = "Available rooms retrieved successfully.";
            }
        } catch (Exception e) {
            message = "Error fetching available rooms: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

    // 🔹 Get Rooms by Hotel ID
    @GetMapping("/hotel/{hotelId}")
    public ResortBookingResponse getRoomsByHotel(@PathVariable Long hotelId) {
        String message = "";
        HttpStatus status = HttpStatus.OK;

        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                message = "Hotel not found.";
                status = HttpStatus.NOT_FOUND;
            } else {
                Hotel hotel = HotelMapper.toEntity(hotelDto);
                List<Rooms> rooms = roomsService.getRoomsByHotel(hotel);
                message = rooms.isEmpty() ? "No rooms found for this hotel." : "Rooms retrieved successfully.";
            }
        } catch (Exception e) {
            message = "Error retrieving rooms by hotel: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

    // 🔹 Update Room
    @PutMapping("/{id}")
    public ResortBookingResponse updateRoom(@PathVariable Long id, @RequestBody Rooms roomDetails) {
        String message = "";
        HttpStatus status = HttpStatus.OK;

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

                roomsService.updateRoom(room);
                message = "Room updated successfully.";
            } else {
                message = "Room not found.";
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            message = "Error updating room: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

    // 🔹 Delete Room
    @DeleteMapping("/{id}")
    public ResortBookingResponse deleteRoom(@PathVariable Long id) {
        String message = "";
        HttpStatus status = HttpStatus.NO_CONTENT;

        try {
            Optional<Rooms> room = roomsService.getRoomById(id);
            if (room.isPresent()) {
                roomsService.deleteRoom(id);
                message = "Room deleted successfully.";
            } else {
                message = "Room not found.";
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            message = "Error deleting room: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

}

