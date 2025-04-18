package com.resortbooking.application.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(length=64)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private boolean isVerified;

    private Boolean isGoogleUser;

    private String profileImageUrl;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id") // Foreign key to roles table
    private Roles role;
    
    
	@OneToMany(mappedBy="user") 
	private List<HotelReview> hotelReviews;
	
//	@OneToMany(mappedBy="user")
//	private List<UserWishlist> wishList;
//	
	@OneToMany(mappedBy="user")
	private List<HotelBooking> hotelBooking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public Boolean getIsGoogleUser() {
        return isGoogleUser;
    }

    public void setIsGoogleUser(Boolean isGoogleUser) {
        this.isGoogleUser = isGoogleUser;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }


    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, email, phoneNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(email, other.email) &&
               Objects.equals(phoneNumber, other.phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + (role != null ? role.getRole() : "null") +
                '}';
    }
}
