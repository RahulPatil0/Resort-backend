package com.resortbooking.application.services;

import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.exception.ResortBookingException;

import java.util.List;

public interface HotelPolicyService {

    HotelPolicyDTO addPolicy(HotelPolicyDTO hotelPolicyDTO) throws ResortBookingException;

    List<HotelPolicyDTO> getAllPolicies() throws ResortBookingException;

    HotelPolicyDTO getPolicyById(Long id) throws ResortBookingException;

    HotelPolicyDTO updatePolicy(Long id, HotelPolicyDTO hotelPolicyDTO) throws ResortBookingException;

    void deletePolicy(Long id) throws ResortBookingException;

    List<HotelPolicyDTO> getPoliciesByHotelId(Long hotelId) throws ResortBookingException;
}
