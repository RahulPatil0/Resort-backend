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

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.Roles;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.RolesService;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

	@Autowired
	private RolesService rolesService;

	// Create a new role
	@PostMapping
	public ResortBookingResponse createRole(@RequestBody Roles role) {
		String message = "";
		HttpStatus status = HttpStatus.CREATED;

		try {
			Roles created = rolesService.createRole(role);
			message = "Role created successfully with ID: " + created.getId();
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			message = "Error creating role: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}

	// 🔹 Get All Roles
	@GetMapping
	public ResortBookingResponse getAllRoles() {
		String message = "";
		HttpStatus status = HttpStatus.OK;

		try {
			List<Roles> roles = rolesService.getAllRoles();
			if (roles.isEmpty()) {
				message = "No roles found.";
				status = HttpStatus.NOT_FOUND;
			} else {
				message = "Roles retrieved successfully.";
			}
		} catch (Exception e) {
			message = "Error fetching roles: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}

	// 🔹 Get Role by ID
	@GetMapping("/{id}")
	public ResortBookingResponse getRoleById(@PathVariable Long id) {
		String message = "";
		HttpStatus status = HttpStatus.OK;

		try {
			Optional<Roles> role = rolesService.getRoleById(id);
			if (role.isPresent()) {
				message = "Role retrieved successfully.";
			} else {
				message = "Role not found.";
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			message = "Error retrieving role by ID: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}

	// 🔹 Update Role
	@PutMapping("/{id}")
	public ResortBookingResponse updateRole(@PathVariable Long id, @RequestBody Roles updatedRole) {
		String message = "";
		HttpStatus status = HttpStatus.OK;

		try {
			Optional<Roles> existing = rolesService.getRoleById(id);
			if (existing.isPresent()) {
				Roles roleToUpdate = existing.get();
				roleToUpdate.setRole(updatedRole.getRole());
				rolesService.updateRole(roleToUpdate);
				message = "Role updated successfully.";
			} else {
				message = "Role not found.";
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			message = "Error updating role: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}

	// 🔹 Delete Role
	@DeleteMapping("/{id}")
	public ResortBookingResponse deleteRole(@PathVariable Long id) {
		String message = "";
		HttpStatus status = HttpStatus.NO_CONTENT;

		try {
			Optional<Roles> role = rolesService.getRoleById(id);
			if (role.isPresent()) {
				rolesService.deleteRole(id);
				message = "Role deleted successfully.";
			} else {
				message = "Role not found.";
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			message = "Error deleting role: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}
}
