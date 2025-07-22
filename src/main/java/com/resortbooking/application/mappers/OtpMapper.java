package com.resortbooking.application.mappers;

import java.security.SecureRandom;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.OtpDTO;
import com.resortbooking.application.models.Otp;

public class OtpMapper {

    public static String generateOTP(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // digits 0–9
        }

        return sb.toString();
    }
    
    public static Otp dtoToEntityMapper(OtpDTO dto) {
    	Otp otp =  new Otp();
    	BeanUtils.copyProperties(dto, otp);
    	return otp;
    }
    
    public static OtpDTO entityToDtoMapper(Otp otp) {
    	OtpDTO dto =  new OtpDTO();
    	BeanUtils.copyProperties(otp, dto);
    	return dto;
    }
}
