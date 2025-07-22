package com.resortbooking.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.dto.OtpDTO;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.OtpService;

@RestController
@RequestMapping("/api/auth")
public class OtpController {
	
	@Autowired
	private OtpService otpService;

    @PostMapping("/request-otp")
    public ResortBookingResponse requestOtp(@RequestBody OtpDTO dto) {
        try {
            String msg = otpService.generateOtp(dto);
            return new ResortBookingResponse(msg, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResortBookingResponse(e.getLocalizedMessage(), HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/verify-otp")
    public ResortBookingResponse verifyOtp(@RequestBody OtpDTO dto) {
    	try {
            String msg = otpService.verifyOtp(dto);
            return new ResortBookingResponse(msg, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResortBookingResponse(e.getLocalizedMessage(), HttpStatusCode.valueOf(500));
        }
    }
}
