package com.resortbooking.application.services;

import org.springframework.stereotype.Service;

import com.resortbooking.application.dto.OtpDTO;
import com.resortbooking.application.exception.ResortBookingException;

@Service
public interface OtpService {

    public String generateOtp(OtpDTO dto) throws ResortBookingException;

    public boolean verifyOtp(String emailOrMobile, String enteredOtp);
}
