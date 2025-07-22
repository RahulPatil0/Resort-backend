package com.resortbooking.application.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_tokens")
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Email or Mobile
	@Column(nullable = false)
	private String key;

	@Column(nullable = false)
	private String otp;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime expiresAt;

	// Optional: Track OTP status (e.g., sent, verified, expired)
	@Column(nullable = false)
	private OtpStatus status;

	// Getters and Setters

	public Long getId() {
		return id;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OtpStatus getStatus() {
		return status;
	}

	public void setStatus(OtpStatus status) {
		this.status = status;
	}

	public enum OtpStatus {
		SENT, VERIFIED, EXPIRED, FAILED
	}

}
