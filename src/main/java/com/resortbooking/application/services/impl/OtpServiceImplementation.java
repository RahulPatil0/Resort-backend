package com.resortbooking.application.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.OtpRepository;
import com.resortbooking.application.dto.OtpDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.OtpMapper;
import com.resortbooking.application.models.Otp;
import com.resortbooking.application.services.EmailService;
import com.resortbooking.application.services.OtpService;
import com.resortbooking.application.services.TwoFactorService;

import io.micrometer.common.util.StringUtils;

@Service
public class OtpServiceImplementation implements OtpService {

	@Autowired
    private TwoFactorService twoFactorService;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private OtpRepository repository;

    public String generateOtp(OtpDTO dto) throws ResortBookingException {
    	String key = dto.getKey();

        if (StringUtils.isEmpty(key)) {
            throw new ResortBookingException("Email or mobile number is required.");
        }

        Otp entity = OtpMapper.dtoToEntityMapper(dto);
        String otp = OtpMapper.generateOTP(6); // FIXED this line
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);
        
        entity.setOtp(otp);
        entity.setExpiresAt(expiry);
        
        if(key.equals("mobile")) {
        	twoFactorService.sendOtp(otp);
        } else if (key.equals("email")) {
        	emailService.sendOtpByEmail(key, otp);
        }
        
        repository.save(entity);
        
        return "OTP sent successfully.";
    }

    public boolean verifyOtp(String emailOrMobile, String enteredOtp) {
//        String storedOtp = otpStore.get(emailOrMobile);
//        return enteredOtp != null && enteredOtp.equals(storedOtp);
    	return false;
    }
}
