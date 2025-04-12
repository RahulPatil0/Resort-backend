package com.resortbooking.application.services;

import java.util.List;

import com.resortbooking.application.dto.HotelPolicyDTO;

public interface HotelPolicyService {
    HotelPolicyDTO addPolicy(HotelPolicyDTO dto);
    List<HotelPolicyDTO> getPoliciesByHotel(Long hotelId);
    void deletePolicy(Long id);
}
