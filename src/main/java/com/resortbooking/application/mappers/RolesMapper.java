package com.resortbooking.application.mappers;

import com.resortbooking.application.dto.RolesDto;
import com.resortbooking.application.models.Roles;

public class RolesMapper {

    // Convert from entity to DTO
    public static RolesDto toDto(Roles roles) {
        if (roles == null) {
            return null;
        }

        return new RolesDto(
                roles.getId(),
                roles.getRole(), // maps 'role' to 'roleName'
                "Role description not available" // Placeholder description (can customize if needed)
        );
    }

    // Convert from DTO to entity
    public static Roles toEntity(RolesDto dto) {
        if (dto == null) {
            return null;
        }

        Roles roles = new Roles();
        roles.setId(dto.getId());
        roles.setRole(dto.getRoleName()); // maps 'roleName' to 'role'

        // Note: We skip setting users here; that should be managed in the User service logic
        return roles;
    }
}
