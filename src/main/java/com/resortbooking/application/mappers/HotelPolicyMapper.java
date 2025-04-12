package com.resortbooking.application.mappers;

import org.springframework.stereotype.Component;

import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPolicy;

@Component
public class HotelPolicyMapper {

    public HotelPolicyDTO toDTO(HotelPolicy entity) {
        HotelPolicyDTO dto = new HotelPolicyDTO();
        dto.setId(entity.getId());
        dto.setPolicyType(entity.getPolicyType());
        dto.setDescription(entity.getDescription());
        dto.setHotelId(entity.getHotel().getId());
        return dto;
    }

    public HotelPolicy toEntity(HotelPolicyDTO dto, Hotel hotel) {
        HotelPolicy policy = new HotelPolicy();
        policy.setId(dto.getId());
        policy.setPolicyType(dto.getPolicyType());
        policy.setDescription(dto.getDescription());
        policy.setHotel(hotel);
        return policy;
    }
}
