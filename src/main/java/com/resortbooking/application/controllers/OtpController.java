package com.resortbooking.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResortBookingResponse<String> requestOtp(@RequestBody OtpDTO dto) {
        try {
            String msg = otpService.generateOtp(dto);
            return new ResortBookingResponse<String>(msg, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResortBookingResponse<String>(e.getLocalizedMessage(),  HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verify-otp")
    public ResortBookingResponse<String> verifyOtp(@RequestBody OtpDTO dto) {
    	try {
            String msg = otpService.verifyOtp(dto);
            return new ResortBookingResponse<String>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResortBookingResponse<String>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
