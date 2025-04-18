package com.resortbooking.application.services;

import java.util.List;

import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.exception.ResortBookingException;

public interface HotelPolicyService {
    HotelPolicyDTO addPolicy(HotelPolicyDTO dto) throws ResortBookingException;
    
    List<HotelPolicyDTO> getPoliciesByHotel(Long hotelId) throws ResortBookingException;
    
    void deletePolicy(Long id) throws ResortBookingException;
}
