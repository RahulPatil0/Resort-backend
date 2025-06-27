package com.resortbooking.application.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPolicy;

@Component
public class HotelPolicyMapper {

    public static HotelPolicyDTO toDTO(HotelPolicy policy) {
        HotelPolicyDTO dto = new HotelPolicyDTO();
        BeanUtils.copyProperties(policy, dto);
        return dto;
    }

    public static HotelPolicy toEntity(HotelPolicyDTO dto, Hotel hotel) {
        HotelPolicy policy = new HotelPolicy();
        BeanUtils.copyProperties(dto, policy);
        return policy;
    }
}
