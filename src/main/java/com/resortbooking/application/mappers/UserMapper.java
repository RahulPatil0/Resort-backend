package com.resortbooking.application.mappers;

import com.resortbooking.application.models.User;
import com.resortbooking.application.dto.UserDTO;

public class UserMapper {

    // Convert User Entity to UserDTO
    public static UserDTO toDto(User user) {
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

    // Convert UserDTO to User Entity
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
}
