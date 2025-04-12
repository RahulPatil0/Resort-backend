package com.resortbooking.application.services.impl;

import com.resortbooking.application.dao.RolesRepository;
import com.resortbooking.application.models.Roles;
import com.resortbooking.application.services.RolesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Roles createRole(Roles role) {
        try {
            return rolesRepository.save(role);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error creating role: " + e.getMessage());
            throw new RuntimeException("Error creating role: " + e.getMessage());
        }
    }

    @Override
    public List<Roles> getAllRoles() {
        try {
            return rolesRepository.findAll();
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching all roles: " + e.getMessage());
            throw new RuntimeException("Error fetching all roles: " + e.getMessage());
        }
    }

    @Override
    public Optional<Roles> getRoleById(Long id) {
        try {
            return rolesRepository.findById(id);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching role by ID: " + e.getMessage());
            throw new RuntimeException("Error fetching role by ID: " + e.getMessage());
        }
    }

    @Override
    public Optional<Roles> getRoleByName(String roleName) {
        try {
            return rolesRepository.findByRole(roleName);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching role by name: " + e.getMessage());
            throw new RuntimeException("Error fetching role by name: " + e.getMessage());
        }
    }

    @Override
    public Roles updateRole(Roles role) {
        try {
            return rolesRepository.save(role);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error updating role: " + e.getMessage());
            throw new RuntimeException("Error updating role: " + e.getMessage());
        }
    }

    @Override
    public void deleteRole(Long id) {
        try {
            rolesRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error deleting role: " + e.getMessage());
            throw new RuntimeException("Error deleting role: " + e.getMessage());
        }
    }
}
