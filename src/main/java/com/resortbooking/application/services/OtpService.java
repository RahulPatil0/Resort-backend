package com.resortbooking.application.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.mappers.OtpMapper;

@Service
public class OtpService {

    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;

    private final Map<String, String> otpStore = new HashMap<>();

    public void generateOtp(String emailOrMobile) {
        // ✅ Using OtpMapper (not OtpUtil)
        String otp = OtpMapper.generateOTP(6);

        otpStore.put(emailOrMobile, otp);

        if (emailOrMobile.contains("@")) {
            // ✅ Email flow
            emailService.sendOtpByEmail(emailOrMobile, otp);
        } else {
            // ✅ SMS flow - corrected method name
            smsService.sendOtpSms(emailOrMobile, otp);
        }
    }

    public boolean verifyOtp(String emailOrMobile, String enteredOtp) {
        String storedOtp = otpStore.get(emailOrMobile);
        return enteredOtp != null && enteredOtp.equals(storedOtp);
    }
}
