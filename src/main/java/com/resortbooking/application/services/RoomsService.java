package com.resortbooking.application.services;

import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Rooms;
import java.util.List;
import java.util.Optional;

public interface RoomsService {
    Rooms createRoom(Rooms room);
    List<Rooms> getAllRooms();
    Optional<Rooms> getRoomById(Long id);
    Rooms updateRoom(Rooms room);
    void deleteRoom(Long id);
    List<Rooms> getAvailableRooms();
    List<Rooms> getRoomsByHotel(Hotel hotel);
    List<Rooms> getAvailableRoomsByHotel(Hotel hotel);
    List<Rooms> getRoomsByType(Hotel hotel, String type);
}
