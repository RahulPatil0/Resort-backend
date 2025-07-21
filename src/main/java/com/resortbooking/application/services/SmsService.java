package com.resortbooking.application.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import java.util.*;

@Service
public class SmsService {

    private final String API_KEY = "pIuPXOLSw0DB6CWQaGsxbMch8j249UzvoZdJkqyt3ENrf1mgl50MlaigQCZ7Fu2RTnxqGv9BwXb6HWrd";
    @Autowired
    private Msg91Service msg91Service;
    public void sendOtpSms(String mobileNumber) {
    	 String otp = String.valueOf((int)(Math.random()*9000)+1000); // Generate 4-digit OTP
         String response = msg91Service.sendOTP(mobile, otp);
         return ResponseEntity.ok("OTP sent. MSG91 Response: " + response);
    }
}
