//package com.resortbooking.application.controllers;
//
//import com.resortbooking.application.dto.HotelDto;
//import com.resortbooking.application.models.Hotel;
//import com.resortbooking.application.models.Rooms;
//import com.resortbooking.application.services.HotelService;
//import com.resortbooking.application.services.RoomsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/rooms")
//@CrossOrigin(origins = "*") // For dev, restrict in prod
//public class RoomsController {
//
//    @Autowired
//    private RoomsService roomsService;
//
//    @Autowired
//    private HotelService hotelService;
//
//    // Create Room
//    @PostMapping
//    public ResponseEntity<Rooms> createRoom(@RequestBody Rooms room) {
//        if (room.getHotel() == null || room.getHotel().getId() == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        HotelDto hotelDto = hotelService.getHotelById(room.getHotel().getId());
//        if (hotelDto == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Hotel hotel = convertDtoToEntity(hotelDto);
//        room.setHotel(hotel);
//        Rooms createdRoom = roomsService.createRoom(room);
//        return ResponseEntity.ok(createdRoom);
//    }
//
//    // Get all rooms
//    @GetMapping
//    public ResponseEntity<List<Rooms>> getAllRooms() {
//        return ResponseEntity.ok(roomsService.getAllRooms());
//    }
//
//    // Get room by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Rooms> getRoomById(@PathVariable Long id) {
//        return roomsService.getRoomById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Get available rooms
//    @GetMapping("/available")
//    public ResponseEntity<List<Rooms>> getAvailableRooms() {
//        return ResponseEntity.ok(roomsService.getAvailableRooms());
//    }
//
//    // Get rooms by hotel ID
//    @GetMapping("/hotel/{hotelId}")
//    public ResponseEntity<List<Rooms>> getRoomsByHotel(@PathVariable Long hotelId) {
//        HotelDto hotelDto = hotelService.getHotelById(hotelId);
//        if (hotelDto == null) {
//            return ResponseEntity.notFound().build();
//        }
//        Hotel hotel = convertDtoToEntity(hotelDto);
//        return ResponseEntity.ok(roomsService.getRoomsByHotel(hotel));
//    }
//
//    // Get available rooms by hotel ID
//    @GetMapping("/hotel/{hotelId}/available")
//    public ResponseEntity<List<Rooms>> getAvailableRoomsByHotel(@PathVariable Long hotelId) {
//        HotelDto hotelDto = hotelService.getHotelById(hotelId);
//        if (hotelDto == null) {
//            return ResponseEntity.notFound().build();
//        }
//        Hotel hotel = convertDtoToEntity(hotelDto);
//        return ResponseEntity.ok(roomsService.getAvailableRoomsByHotel(hotel));
//    }
//
//    // Get rooms by type in a hotel
//    @GetMapping("/hotel/{hotelId}/type/{type}")
//    public ResponseEntity<List<Rooms>> getRoomsByType(
//            @PathVariable Long hotelId,
//            @PathVariable String type
//    ) {
//        HotelDto hotelDto = hotelService.getHotelById(hotelId);
//        if (hotelDto == null) {
//            return ResponseEntity.notFound().build();
//        }
//        Hotel hotel = convertDtoToEntity(hotelDto);
//        return ResponseEntity.ok(roomsService.getRoomsByType(hotel, type));
//    }
//
//    // Update Room
//    @PutMapping("/{id}")
//    public ResponseEntity<Rooms> updateRoom(@PathVariable Long id, @RequestBody Rooms roomDetails) {
//        Optional<Rooms> existingRoomOpt = roomsService.getRoomById(id);
//        if (!existingRoomOpt.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Rooms existingRoom = existingRoomOpt.get();
//
//        existingRoom.setRoomNumber(roomDetails.getRoomNumber());
//        existingRoom.setType(roomDetails.getType());
//        existingRoom.setPricePerNight(roomDetails.getPricePerNight());
//        existingRoom.setCapacity(roomDetails.getCapacity());
//        existingRoom.setIsAvailable(roomDetails.getIsAvailable());
//        existingRoom.setDescription(roomDetails.getDescription());
//
//        Rooms updatedRoom = roomsService.updateRoom(existingRoom);
//        return ResponseEntity.ok(updatedRoom);
//    }
//
//    // Delete Room
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
//        Optional<Rooms> room = roomsService.getRoomById(id);
//        if (!room.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        roomsService.deleteRoom(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    // Utility: Convert HotelDto to Hotel Entity
//    private Hotel convertDtoToEntity(HotelDto dto) {
//        Hotel hotel = new Hotel();
//        hotel.setId(dto.getId());
//        hotel.setHotelName(dto.getHotelName());
//        hotel.setAddress(dto.getAddress());
//        hotel.setDescription(dto.getDescription());
//        hotel.setPricePerNight(dto.getPricePerNight());
//        hotel.setRating(dto.getRating());
//        hotel.setImageUrl(dto.getImageUrl());
//        hotel.setIsAvailable(dto.getIsAvailable());
//        hotel.setWebsite(dto.getWebsite());
//        hotel.setLatitude(dto.getLatitude());
//        hotel.setLongitude(dto.getLongitude());
//        return hotel;
//    }
//}

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
@CrossOrigin(origins = "*") // For dev, restrict in prod
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

    // 🔹 Get All Rooms
    @GetMapping
    public ResortBookingResponse getAllRooms() {
        String message = "";
        HttpStatus status = HttpStatus.OK;

        try {
            List<Rooms> rooms = roomsService.getAllRooms();
            if (rooms.isEmpty()) {
                message = "No rooms found.";
                status = HttpStatus.NOT_FOUND;
            } else {
                message = "Rooms retrieved successfully.";
            }
        } catch (Exception e) {
            message = "Error retrieving rooms: " + e.getMessage();
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

    // 🔹 Get Available Rooms by Hotel ID
    @GetMapping("/hotel/{hotelId}/available")
    public ResortBookingResponse getAvailableRoomsByHotel(@PathVariable Long hotelId) {
        String message = "";
        HttpStatus status = HttpStatus.OK;

        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                message = "Hotel not found.";
                status = HttpStatus.NOT_FOUND;
            } else {
                Hotel hotel = HotelMapper.toEntity(hotelDto);
                List<Rooms> availableRooms = roomsService.getAvailableRoomsByHotel(hotel);
                message = availableRooms.isEmpty() ? "No available rooms in this hotel." : "Available rooms retrieved successfully.";
            }
        } catch (Exception e) {
            message = "Error fetching available rooms by hotel: " + e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResortBookingResponse(message, status);
    }

    // 🔹 Get Rooms by Type
    @GetMapping("/hotel/{hotelId}/type/{type}")
    public ResortBookingResponse getRoomsByType(@PathVariable Long hotelId, @PathVariable String type) {
        String message = "";
        HttpStatus status = HttpStatus.OK;

        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                message = "Hotel not found.";
                status = HttpStatus.NOT_FOUND;
            } else {
                Hotel hotel = HotelMapper.toEntity(hotelDto);
                List<Rooms> rooms = roomsService.getRoomsByType(hotel, type);
                message = rooms.isEmpty() ? "No rooms found for type: " + type : "Rooms of type '" + type + "' retrieved successfully.";
            }
        } catch (Exception e) {
            message = "Error retrieving rooms by type: " + e.getMessage();
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

