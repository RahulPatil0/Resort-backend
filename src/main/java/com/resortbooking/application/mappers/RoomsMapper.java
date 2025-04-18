package com.resortbooking.application.mappers;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.dto.RoomsDto;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Rooms;

public class RoomsMapper {

    public static RoomsDto toDto(Rooms room) {
        if (room == null) {
            return null;
        }

        Hotel hotel = room.getHotel();
        HotelDto hotelDTO = new HotelDto();
        
        BeanUtils.copyProperties(hotel, hotelDTO);

        return new RoomsDto(
                room.getId(),
                room.getRoomNumber(),
                room.getType(),
                room.getPricePerNight(),
                room.getIsAvailable() != null && room.getIsAvailable() ? "Available" : "Unavailable",
                room.getCapacity(),
                room.getDescription(),
                hotelDTO
        );
    }

    public static Rooms toEntity(RoomsDto dto, Hotel hotel) {
        if (dto == null) {
            return null;
        }

        Rooms room = new Rooms();
        room.setId(dto.getId());
        room.setRoomNumber(dto.getRoomNumber());
        room.setType(dto.getType());
        room.setPricePerNight(dto.getPricePerNight());
        room.setCapacity(dto.getCapacity());
        room.setDescription(dto.getDescription());
        room.setIsAvailable("Available".equalsIgnoreCase(dto.getStatus()));
        room.setHotel(hotel);
        return room;
    }
}
