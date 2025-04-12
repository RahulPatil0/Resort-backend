package com.resortbooking.application.services;

import com.resortbooking.application.models.Roles;

import java.util.List;
import java.util.Optional;

public interface RolesService {

    Roles createRole(Roles role);

    List<Roles> getAllRoles();

    Optional<Roles> getRoleById(Long id);

    Optional<Roles> getRoleByName(String roleName);

    Roles updateRole(Roles role);

    void deleteRole(Long id);
}
