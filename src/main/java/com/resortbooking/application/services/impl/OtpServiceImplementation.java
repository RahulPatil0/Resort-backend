package com.resortbooking.application.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.OtpRepository;
import com.resortbooking.application.dto.OtpDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.OtpMapper;
import com.resortbooking.application.models.Otp;
import com.resortbooking.application.models.Otp.OtpStatus;
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
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus(OtpStatus.SENT);
        
        if(!key.contains("@")) {
        	twoFactorService.sendOtp(key, otp);
        } else if (key.contains("@")) {
        	emailService.sendOtpByEmail(key, otp);
        }
        
        repository.save(entity);
        
        return "OTP sent successfully.";
    }

    public String verifyOtp(OtpDTO dto) throws ResortBookingException {

    	String key = dto.getKey();
        String inputOtp = dto.getOtp();

        if (StringUtils.isEmpty(key)) {
            throw new ResortBookingException("Email or mobile number is required.");
        }
        
        if (StringUtils.isEmpty(inputOtp)) {
            throw new ResortBookingException("Invalid Otp");
        }
        
        Optional<Otp> savedOtp = repository.findByKeyAndOtp(key, inputOtp);
        
        if(savedOtp.isEmpty()) {
        	throw new ResortBookingException("Invalid Otp");
        }
        
        Otp otp = savedOtp.get();

        if (LocalDateTime.now().isAfter(otp.getExpiresAt())) {
            repository.delete(otp);
            throw new ResortBookingException("Otp has been expired. Try again!!");
        }

        if (!otp.getOtp().equals(inputOtp)) {
        	throw new ResortBookingException("Invalid Otp");
        } else {
        	repository.delete(otp);
        	return key + " Verified successfully";
        }

    }
}
