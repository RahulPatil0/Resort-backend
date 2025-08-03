package com.resortbooking.application.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.HotelDto;
import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.dto.MediaDto;
import com.resortbooking.application.models.Amenity;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPolicy;
import com.resortbooking.application.models.Media;

public class HotelMapper {

    public static HotelDto toDto(Hotel hotel) {
        HotelDto dto = new HotelDto();
        
        BeanUtils.copyProperties(hotel, dto);
        
        List<MediaDto> hotelPhotosDtos = new ArrayList<>();
        
        for(Media media : hotel.getHotelPhotos()) {
        	MediaDto hotelPhotosDto = MediaMapper.toDto(media);
        	hotelPhotosDtos.add(hotelPhotosDto);
        }
        dto.setMedia(hotelPhotosDtos);
        
        List<String> amenities = new ArrayList<>();
        for(Amenity amenity: hotel.getAmenities()) {
        	amenities.addAll(amenity.getAmenities());
        }
        dto.setAmenity(amenities);
        
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
