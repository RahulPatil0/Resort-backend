package com.resortbooking.application.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.AmenityDTO;
import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.dto.HotelPhotosDto;
import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.models.Amenity;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPhotos;
import com.resortbooking.application.models.HotelPolicy;

public class HotelMapper {

    public static HotelDto toDto(Hotel hotel) {
        HotelDto dto = new HotelDto();
        
        BeanUtils.copyProperties(hotel, dto);
        
        List<HotelPhotosDto> hotelPhotosDtos = new ArrayList<>();
        
        for(HotelPhotos hotelPhotos : hotel.getHotelPhotos()) {
        	HotelPhotosDto hotelPhotosDto = HotelPhotosMapper.toDto(hotelPhotos);
        	hotelPhotosDtos.add(hotelPhotosDto);
        }
        dto.setHotelPhotos(hotelPhotosDtos);
        
        Set<AmenityDTO> amenties = new HashSet<>();
        for(Amenity amenity: hotel.getAmenities()) {
        	AmenityDTO amenityDTO = AmenityMapper.toDTO(amenity);
        	amenties.add(amenityDTO);
        }
        dto.setAmenities(amenties);
        
        Set<HotelPolicyDTO> policyDTOs = new HashSet<>();
        for(HotelPolicy policy : hotel.getPolicies()) {
        	HotelPolicyDTO policyDTO = HotelPolicyMapper.toDTO(policy);
        	policyDTOs.add(policyDTO);
        }
        dto.setPolicyDetails(policyDTOs);
        return dto;
    }

    public static Hotel toEntity(HotelDto dto) {
        Hotel hotel = new Hotel();

        BeanUtils.copyProperties(dto, hotel);
        
        return hotel;
    }
}
