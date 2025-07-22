//package com.resortbooking.application.services;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import java.util.*;
//
//@Service
//public class SmsService {
//
//    private final String API_KEY = "34df5e4b-6706-11f0-a562-0200cd936042";
//    @Autowired
//    private TwoFactorService msg91Service;
//    
//    public ResponseEntity<?> sendOtpSms(String mobileNumber) {
//    	 String otp = String.valueOf((int)(Math.random()*9000)+1000); // Generate 4-digit OTP
//         String response = TwoFactorService.sendOTP(mobileNumber, otp);
//         return ResponseEntity.ok("OTP sent. MSG91 Response: " + response);
//    }
//}
package com.resortbooking.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    private TwoFactorService twoFactorService;

    public ResponseEntity<?> sendOtpSms(String mobileNumber) {
        String otp = String.valueOf((int)(Math.random() * 9000) + 1000); // Generate 4-digit OTP
        String response = twoFactorService.sendOtp(mobileNumber); // uses 2Factor API
        return ResponseEntity.ok("OTP sent. 2Factor Response: " + response);
    }
}
