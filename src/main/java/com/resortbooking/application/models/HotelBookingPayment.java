//package com.resortbooking.application.models;
//
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//@Entity
//@Table(name = "hotel_booking_payments")
//public class HotelBookingPayment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
//    @Column(name="payment_method")
//    private String paymentMethod; 
//    
//    @Column(name="transaction_id")
//    private String transactionId;
//
//    @Column(name="amount")
//    private BigDecimal amount;
//
//    @Column(name="payment_status")
//    private String paymentStatus;
//    
//    @Column(name="paymentDate")
//    private LocalDateTime paymentDate;
//
//    @Column(name="paymentGateway")
//    private String paymentGateway; // e.g. Razorpay, Stripe, etc.
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "booking_id", nullable = false)
//    private HotelBooking booking;
//
//    // Getters and Setters
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }
//
//    public String getTransactionId() {
//        return transactionId;
//    }
//
//    public void setTransactionId(String transactionId) {
//        this.transactionId = transactionId;
//    }
//
//    public BigDecimal getAmount() {
//        return amount;
//    }
//
//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }
//
//    public String getPaymentStatus() {
//        return paymentStatus;
//    }
//
//    public void setPaymentStatus(String paymentStatus) {
//        this.paymentStatus = paymentStatus;
//    }
//
//    public LocalDateTime getPaymentDate() {
//        return paymentDate;
//    }
//
//    public void setPaymentDate(LocalDateTime paymentDate) {
//        this.paymentDate = paymentDate;
//    }
//
//    public String getPaymentGateway() {
//        return paymentGateway;
//    }
//
//    public void setPaymentGateway(String paymentGateway) {
//        this.paymentGateway = paymentGateway;
//    }
//
//    public HotelBooking getBooking() {
//        return booking;
//    }
//
//    public void setBooking(HotelBooking booking) {
//        this.booking = booking;
//    }
//
//    // equals and hashCode
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof HotelBookingPayment)) return false;
//        HotelBookingPayment that = (HotelBookingPayment) o;
//        return Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    // toString
//
//    @Override
//    public String toString() {
//        return "HotelBookingPayment{" +
//                "id=" + id +
//                ", amount=" + amount +
//                ", paymentStatus='" + paymentStatus + '\'' +
//                ", paymentDate=" + paymentDate +
//                ", transactionId='" + transactionId + '\'' +
//                '}';
//    }
//}
package com.resortbooking.application.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "hotel_booking_payments")
public class HotelBookingPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_gateway")
    private String paymentGateway; // e.g., Razorpay, Stripe

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private HotelBooking hotelBooking;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }

    public void setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public HotelBooking getHotelBooking() {
        return hotelBooking;
    }

    public void setHotelBooking(HotelBooking hotelBooking) {
        this.hotelBooking = hotelBooking;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelBookingPayment)) return false;
        HotelBookingPayment that = (HotelBookingPayment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString

    @Override
    public String toString() {
        return "HotelBookingPayment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentDate=" + paymentDate +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}

