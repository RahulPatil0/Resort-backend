//package com.resortbooking.application.controllers;
//
//import com.resortbooking.application.models.Roles;
//import com.resortbooking.application.services.RolesService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/roles")
//public class RolesController {
//
//    @Autowired
//    private RolesService rolesService;
//
//    // Create a new role
//    @PostMapping
//    public ResponseEntity<Roles> createRole(@RequestBody Roles role) {
//        Roles created = rolesService.createRole(role);
//        return ResponseEntity
//                .created(URI.create("/api/roles/" + created.getId()))
//                .body(created);
//    }
//
//    // Retrieve all roles
//    @GetMapping
//    public ResponseEntity<List<Roles>> getAllRoles() {
//        List<Roles> roles = rolesService.getAllRoles();
//        return ResponseEntity.ok(roles);
//    }
//
//    // Retrieve role by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Roles> getRoleById(@PathVariable Long id) {
//        return rolesService.getRoleById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Retrieve role by name
//    @GetMapping("/search")
//    public ResponseEntity<Roles> getRoleByName(@RequestParam("name") String roleName) {
//        return rolesService.getRoleByName(roleName)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Update a role
//    @PutMapping("/{id}")
//    public ResponseEntity<Roles> updateRole(@PathVariable Long id, @RequestBody Roles updatedRole) {
//        Optional<Roles> existing = rolesService.getRoleById(id);
//
//        if (existing.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Roles roleToUpdate = existing.get();
//        roleToUpdate.setRole(updatedRole.getRole());
//
//        Roles saved = rolesService.updateRole(roleToUpdate);
//        return ResponseEntity.ok(saved);
//    }
//
//    // Delete a role
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
//        if (rolesService.getRoleById(id).isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        rolesService.deleteRole(id);
//        return ResponseEntity.noContent().build();
//    }
//}

package com.resortbooking.application.controllers;

import com.resortbooking.application.models.Roles;
import com.resortbooking.application.services.RolesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    // Create a new role
    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody Roles role) {
        try {
            Roles created = rolesService.createRole(role);
            return ResponseEntity
                    .created(URI.create("/api/roles/" + created.getId()))
                    .body(created);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating role: " + e.getMessage());
        }
    }

    // Retrieve all roles
    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        try {
            List<Roles> roles = rolesService.getAllRoles();
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching roles: " + e.getMessage());
        }
    }

    // Retrieve role by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        try {
            return rolesService.getRoleById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving role by ID: " + e.getMessage());
        }
    }

    // Retrieve role by name
    @GetMapping("/search")
    public ResponseEntity<?> getRoleByName(@RequestParam("name") String roleName) {
        try {
            return rolesService.getRoleByName(roleName)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving role by name: " + e.getMessage());
        }
    }

    // Update a role
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Roles updatedRole) {
        try {
            Optional<Roles> existing = rolesService.getRoleById(id);

            if (existing.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Roles roleToUpdate = existing.get();
            roleToUpdate.setRole(updatedRole.getRole());

            Roles saved = rolesService.updateRole(roleToUpdate);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error updating role: " + e.getMessage());
        }
    }

    // Delete a role
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        try {
            if (rolesService.getRoleById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            rolesService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting role: " + e.getMessage());
        }
    }
}

