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

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Rooms;
import com.resortbooking.application.services.HotelService;
import com.resortbooking.application.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Rooms> createRoom(@RequestBody Rooms room) {
        try {
            if (room.getHotel() == null || room.getHotel().getId() == null) {
                return ResponseEntity.badRequest().body(null);
            }

            HotelDto hotelDto = hotelService.getHotelById(room.getHotel().getId());
            if (hotelDto == null) {
                return ResponseEntity.notFound().build();
            }

            Hotel hotel = convertDtoToEntity(hotelDto);
            room.setHotel(hotel);
            Rooms createdRoom = roomsService.createRoom(room);
            return ResponseEntity.ok(createdRoom);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get all rooms
    @GetMapping
    public ResponseEntity<List<Rooms>> getAllRooms() {
        try {
            return ResponseEntity.ok(roomsService.getAllRooms());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get room by ID
    @GetMapping("/{id}")
    public ResponseEntity<Rooms> getRoomById(@PathVariable Long id) {
        try {
            return roomsService.getRoomById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get available rooms
    @GetMapping("/available")
    public ResponseEntity<List<Rooms>> getAvailableRooms() {
        try {
            return ResponseEntity.ok(roomsService.getAvailableRooms());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get rooms by hotel ID
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rooms>> getRoomsByHotel(@PathVariable Long hotelId) {
        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                return ResponseEntity.notFound().build();
            }
            Hotel hotel = convertDtoToEntity(hotelDto);
            return ResponseEntity.ok(roomsService.getRoomsByHotel(hotel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get available rooms by hotel ID
    @GetMapping("/hotel/{hotelId}/available")
    public ResponseEntity<List<Rooms>> getAvailableRoomsByHotel(@PathVariable Long hotelId) {
        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                return ResponseEntity.notFound().build();
            }
            Hotel hotel = convertDtoToEntity(hotelDto);
            return ResponseEntity.ok(roomsService.getAvailableRoomsByHotel(hotel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get rooms by type in a hotel
    @GetMapping("/hotel/{hotelId}/type/{type}")
    public ResponseEntity<List<Rooms>> getRoomsByType(@PathVariable Long hotelId, @PathVariable String type) {
        try {
            HotelDto hotelDto = hotelService.getHotelById(hotelId);
            if (hotelDto == null) {
                return ResponseEntity.notFound().build();
            }
            Hotel hotel = convertDtoToEntity(hotelDto);
            return ResponseEntity.ok(roomsService.getRoomsByType(hotel, type));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update Room
    @PutMapping("/{id}")
    public ResponseEntity<Rooms> updateRoom(@PathVariable Long id, @RequestBody Rooms roomDetails) {
        try {
            Optional<Rooms> existingRoomOpt = roomsService.getRoomById(id);
            if (!existingRoomOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Rooms existingRoom = existingRoomOpt.get();

            existingRoom.setRoomNumber(roomDetails.getRoomNumber());
            existingRoom.setType(roomDetails.getType());
            existingRoom.setPricePerNight(roomDetails.getPricePerNight());
            existingRoom.setCapacity(roomDetails.getCapacity());
            existingRoom.setIsAvailable(roomDetails.getIsAvailable());
            existingRoom.setDescription(roomDetails.getDescription());

            Rooms updatedRoom = roomsService.updateRoom(existingRoom);
            return ResponseEntity.ok(updatedRoom);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete Room
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        try {
            Optional<Rooms> room = roomsService.getRoomById(id);
            if (!room.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            roomsService.deleteRoom(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Utility: Convert HotelDto to Hotel Entity
    private Hotel convertDtoToEntity(HotelDto dto) {
        Hotel hotel = new Hotel();
        hotel.setId(dto.getId());
        hotel.setHotelName(dto.getHotelName());
        hotel.setAddress(dto.getAddress());
        hotel.setDescription(dto.getDescription());
        hotel.setPricePerNight(dto.getPricePerNight());
        hotel.setRating(dto.getRating());
        hotel.setImageUrl(dto.getImageUrl());
        hotel.setIsAvailable(dto.getIsAvailable());
        hotel.setWebsite(dto.getWebsite());
        hotel.setLatitude(dto.getLatitude());
        hotel.setLongitude(dto.getLongitude());
        return hotel;
    }
}

