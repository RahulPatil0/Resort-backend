package com.resortbooking.application.mappers;

import com.resortbooking.application.models.User;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.UserDTO;

public class UserMapper {

    // Convert User Entity to UserDTO
    public static UserDTO toDto(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    // Convert UserDTO to User Entity
    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
