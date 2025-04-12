package com.resortbooking.application.dto;

import com.resortbooking.application.models.User;

import java.time.LocalDateTime;

public class UserDTO {

    private Long id;
    private String email;
    private String phoneNumber;
    private String fullName;
    private LocalDateTime registeredAt;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String phoneNumber, String fullName, LocalDateTime registeredAt) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.registeredAt = registeredAt;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    // Conversion: User → UserDTO
    public static UserDTO fromEntity(User user) {
        if (user == null) return null;

        String fullName = (user.getFirstName() != null ? user.getFirstName() : "") +
                          (user.getLastName() != null ? " " + user.getLastName() : "");

        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getPhoneNumber(),
                fullName.trim(),
                user.getCreatedAt()
        );
    }

    // Conversion: UserDTO → User
    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());

        if (dto.getFullName() != null && dto.getFullName().contains(" ")) {
            String[] names = dto.getFullName().split(" ", 2);
            user.setFirstName(names[0]);
            user.setLastName(names[1]);
        } else {
            user.setFirstName(dto.getFullName());
            user.setLastName("");
        }

        user.setCreatedAt(dto.getRegisteredAt());

        return user;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", registeredAt=" + registeredAt +
                '}';
    }
}
