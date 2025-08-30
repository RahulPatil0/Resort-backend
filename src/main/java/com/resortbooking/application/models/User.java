//package com.resortbooking.application.models;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//
//@EnableJpaAuditing@EntityListeners(AuditingEntityListener.class)
//@Entity
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String firstName;
//
//    @Column(length=64)
//    private String lastName;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    @Column(nullable = false, length = 64)
//    private String password;
//
//    @Column(nullable = false, unique = true)
//    private String phoneNumber;
//
//    private boolean isVerified;
//
//    private Boolean isGoogleUser;
//
//    private String profileImageUrl;
//
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime lastModifiedAt;
//
//    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//	@JoinTable(name = "users_roles", 
//	joinColumns = {@JoinColumn(name = "user_id")}, 
//	inverseJoinColumns = {@JoinColumn(name = "role_id")})
//	private Set<Roles> roles = new HashSet<Roles>();    
//    
//	@OneToMany(mappedBy="user") 
//	private List<HotelReview> hotelReviews;
//	
//	@OneToMany(mappedBy="user")
//	private List<UserWishlist> wishList;
////	
//	@OneToMany(mappedBy="user")
//	private List<HotelBooking> hotelBooking;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//
//    public boolean isVerified() {
//        return isVerified;
//    }
//
//    public void setVerified(boolean verified) {
//        isVerified = verified;
//    }
//
//    public Boolean getIsGoogleUser() {
//        return isGoogleUser;
//    }
//
//    public void setIsGoogleUser(Boolean isGoogleUser) {
//        this.isGoogleUser = isGoogleUser;
//    }
//
//    public String getProfileImageUrl() {
//        return profileImageUrl;
//    }
//
//    public void setProfileImageUrl(String profileImageUrl) {
//        this.profileImageUrl = profileImageUrl;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getLastModifiedAt() {
//        return lastModifiedAt;
//    }
//
//    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
//        this.lastModifiedAt = lastModifiedAt;
//    }
//
//    public Set<Roles> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Roles> roles) {
//		this.roles = roles;
//	}
//
//	@Override
//    public int hashCode() {
//        return Objects.hash(id, email, phoneNumber);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (!(obj instanceof User)) return false;
//        User other = (User) obj;
//        return Objects.equals(id, other.id) &&
//               Objects.equals(email, other.email) &&
//               Objects.equals(phoneNumber, other.phoneNumber);
//    }
//}

package com.resortbooking.application.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@EnableJpaAuditing@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", 
	joinColumns = {@JoinColumn(name = "user_id")}, 
	inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Set<Roles> roles = new HashSet<Roles>();    
    
	@OneToMany(mappedBy="user") 
	private List<HotelReview> hotelReviews;
	
	@OneToMany(mappedBy="user")
	private List<UserWishlist> wishList;
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

    public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
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
}

