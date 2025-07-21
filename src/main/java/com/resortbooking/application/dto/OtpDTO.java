package com.resortbooking.application.dto;

public class OtpDTO {

    private String key; // email or mobile number
    private String otp;
    private long expiryTime;

    public OtpDTO() {}

    public OtpDTO(String key, String otp, long expiryTime) {
        this.key = key;
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
}
