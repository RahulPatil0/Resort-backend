package com.resortbooking.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * DTO for Hotel Booking Payment transaction details.
 */
public class HotelBookingPaymentDTO {

    private Long id;
    private UserDTO user;
    private HotelBookingDto booking;
    private String paymentMethod;
    private String transactionId;
    private BigDecimal amount;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paidAt;

    // Constructors

    public HotelBookingPaymentDTO() {
    }

    public HotelBookingPaymentDTO(Long id, UserDTO user, HotelBookingDto booking,
                                  String paymentMethod, String transactionId,
                                  BigDecimal amount, String status, LocalDateTime paidAt) {
        this.id = id;
        this.user = user;
        this.booking = booking;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.amount = amount;
        this.status = status;
        this.paidAt = paidAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public HotelBookingDto getBooking() {
        return booking;
    }

    public void setBooking(HotelBookingDto booking) {
        this.booking = booking;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    // toString

    @Override
    public String toString() {
        return "HotelBookingPaymentDTO{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) +
                ", booking=" + (booking != null ? booking.getId() : null) +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", paidAt=" + paidAt +
                '}';
    }
}
