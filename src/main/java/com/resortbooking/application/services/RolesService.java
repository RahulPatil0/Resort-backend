package com.resortbooking.application.services;

import com.resortbooking.application.dto.RolesDto;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Roles;

import java.util.List;
import java.util.Optional;

public interface RolesService {

    Roles createRole(RolesDto role) throws ResortBookingException;

    List<Roles> getAllRoles() throws ResortBookingException;

    Optional<Roles> getRoleById(Long id) throws ResortBookingException;

    Optional<Roles> getRoleByName(String roleName) throws ResortBookingException;

    Roles updateRole(Roles role) throws ResortBookingException;

    void deleteRole(Long id) throws ResortBookingException;
}
