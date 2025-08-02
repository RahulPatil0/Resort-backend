package com.resortbooking.application.mappers;

import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.models.HotelPolicy;
import com.resortbooking.application.models.Hotel;

public class HotelPolicyMapper {

    public static HotelPolicy toEntity(HotelPolicyDTO dto, Hotel hotel) {
        HotelPolicy policy = new HotelPolicy();
        policy.setId(dto.getId());
        policy.setPolicyType(dto.getPolicyType());
        policy.setDescription(dto.getDescription());
        policy.setHotel(hotel);
        return policy;
    }

    public static HotelPolicyDTO toDTO(HotelPolicy policy) {
        return new HotelPolicyDTO(
            policy.getId(),
            policy.getPolicyType(),
            policy.getDescription(),
            policy.getHotel() != null ? policy.getHotel().getId() : null
        );
    }
}
