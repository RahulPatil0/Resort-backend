package com.resortbooking.application.controllers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.resortbooking.application.dto.OtpDTO;
import com.resortbooking.application.mappers.OtpMapper;
import com.resortbooking.application.services.EmailService;
import com.resortbooking.application.services.SmsService;

@RestController
@RequestMapping("/api/auth")
public class OtpController {

    private final Map<String, OtpDTO> otpStore = new ConcurrentHashMap<>();
    private final EmailService emailService;
    private final SmsService smsService;

    public OtpController(EmailService emailService, SmsService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    @PostMapping("/request-otp")
    public ResponseEntity<String> requestOtp(@RequestBody Map<String, String> request) {
        String key = request.get("emailOrMobile");

        if (key == null || key.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email or mobile number is required.");
        }

        String otp = OtpMapper.generateOTP(6); // FIXED this line
        long expiry = System.currentTimeMillis() + (5 * 60 * 1000); // 5 min

        otpStore.put(key, new OtpDTO(key, otp, expiry));

        try {
            if (key.contains("@")) {
                emailService.sendOtpByEmail(key, otp);
            } else {
                smsService.sendOtpSms(key); // FIXED method name
            }
            return ResponseEntity.ok("OTP sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to send OTP: " + e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> request) {
        String key = request.get("emailOrMobile");
        String inputOtp = request.get("otp");

        if (key == null || inputOtp == null) {
            return ResponseEntity.badRequest().body("Missing email/mobile or OTP.");
        }

        OtpDTO savedOtp = otpStore.get(key);
        if (savedOtp == null) {
            return ResponseEntity.badRequest().body("OTP not found or already used.");
        }

        if (System.currentTimeMillis() > savedOtp.getExpiryTime()) {
            otpStore.remove(key);
            return ResponseEntity.badRequest().body("OTP expired.");
        }

        if (!savedOtp.getOtp().equals(inputOtp)) {
            return ResponseEntity.badRequest().body("Invalid OTP.");
        }

        otpStore.remove(key); // One-time use
        return ResponseEntity.ok("OTP verified successfully.");
    }
}
