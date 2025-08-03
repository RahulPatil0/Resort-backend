package com.resortbooking.application.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.RoomsDto;
import com.resortbooking.application.models.Amenity;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.Rooms;

public class RoomsMapper {

	public static RoomsDto toDto(Rooms room) {
		if (room == null) {
			return null;
		}
		RoomsDto dto = new RoomsDto();
		
		List<String> amenities = new ArrayList<>();
        for(Amenity amenity: room.getAmenities()) {
        	amenities.addAll(amenity.getAmenities());
        }
        dto.setAmenity(amenities);

		BeanUtils.copyProperties(room, dto);
		return dto;

	}

	public static Rooms toEntity(RoomsDto dto, Hotel hotel) {
		if (dto == null) {
			return null;
		}

		Rooms room = new Rooms();
		BeanUtils.copyProperties(hotel, room);
		room.setHotel(hotel);
		return room;
	}
}
