package com.resortbooking.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    private TwoFactorService twoFactorService;

    public ResponseEntity<?> sendOtpSms(String mobileNumber, String otp) {
        String otpVal = String.valueOf((int)(Math.random() * 9000) + 1000); // Generate 4-digit OTP
        String response = twoFactorService.sendOtp(mobileNumber); // uses 2Factor API
        return ResponseEntity.ok("OTP sent. 2Factor Response: " + response);
    }
}
