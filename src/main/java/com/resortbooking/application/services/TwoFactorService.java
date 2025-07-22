package com.resortbooking.application.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TwoFactorService {

    @Value("${twofactor.api.key}")
    private String apiKey;

    @Value("${twofactor.api.url}")
    private String apiBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    // ✅ Send OTP
    public String sendOtp(String mobileNumber, String otp) {
        String url = apiBaseUrl + apiKey + "/SMS/" + mobileNumber +"/" + otp + "/OTP1";
        return restTemplate.getForObject(url, String.class);
    }

    // ✅ Verify OTP
    public String verifyOtp(String sessionId, String otp) {
        String url = apiBaseUrl + apiKey + "/SMS/VERIFY/" + sessionId + "/" + otp;
        return restTemplate.getForObject(url, String.class);
    }
}
