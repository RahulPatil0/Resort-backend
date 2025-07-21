package com.resortbooking.application.mappers;

import java.security.SecureRandom;

public class OtpMapper {

    public static String generateOTP(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // digits 0–9
        }

        return sb.toString();
    }
}
