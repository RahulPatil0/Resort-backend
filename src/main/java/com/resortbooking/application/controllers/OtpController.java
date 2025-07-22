package com.resortbooking.application.controllers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.resortbooking.application.dto.OtpDTO;
import com.resortbooking.application.mappers.OtpMapper;
import com.resortbooking.application.services.EmailService;
import com.resortbooking.application.services.OtpService;
import com.resortbooking.application.services.SmsService;
import com.resortbooking.application.services.TwoFactorService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/api/auth")
public class OtpController {
	
	@Autowired
	private OtpService otpService;

	@Autowired
    private EmailService emailService;
	
	@Autowired
    private SmsService smsService;

    @PostMapping("/request-otp")
    public ResponseEntity<String> requestOtp(@RequestBody OtpDTO dto) {
        try {
            String msg = otpService.generateOtp(dto);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to send OTP: " + e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody Map<String, String> request) {
//        String key = request.get("emailOrMobile");
//        String inputOtp = request.get("otp");
//
//        if (key == null || inputOtp == null) {
//            return ResponseEntity.badRequest().body("Missing email/mobile or OTP.");
//        }
//
//        OtpDTO savedOtp = otpStore.get(key);
//        if (savedOtp == null) {
//            return ResponseEntity.badRequest().body("OTP not found or already used.");
//        }
//
//        if (System.currentTimeMillis() > savedOtp.getExpiryTime()) {
//            otpStore.remove(key);
//            return ResponseEntity.badRequest().body("OTP expired.");
//        }
//
//        if (!savedOtp.getOtp().equals(inputOtp)) {
//            return ResponseEntity.badRequest().body("Invalid OTP.");
//        }
//
//        otpStore.remove(key); // One-time use
    	TwoFactorService twoFactor = new TwoFactorService();
        return twoFactor.sendOtp(request.get("mobile"));
    }
}
