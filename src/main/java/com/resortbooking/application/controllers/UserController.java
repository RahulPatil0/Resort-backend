package com.resortbooking.application.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.models.User;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	// Get user by email
	@GetMapping("/email")
	public ResortBookingResponse getUserByEmail(@RequestParam String email) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";

		try {
			Optional<User> userOpt = userService.getUserByEmail(email);
			if (userOpt.isPresent()) {
				status = HttpStatus.OK;
				return new ResortBookingResponse(userOpt.get(), status);
			} else {
				throw new ResortBookingException("User not found with email: " + email);
			}
		} catch (ResortBookingException e) {
			message = e.getMessage();
		} catch (Exception e) {
			message = "Error retrieving user: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}

	// Get all users
	@GetMapping
	public ResortBookingResponse getAllUsers() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";

		try {
			List<User> users = userService.getAllUsers();
			status = HttpStatus.OK;
			return new ResortBookingResponse(users, status);
		} catch (Exception e) {
			message = "Error retrieving user list: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}

	// Delete user by ID
	@DeleteMapping("/{id}")
	public ResortBookingResponse deleteUser(@PathVariable Long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";

		try {
			Optional<User> userOpt = userService.getUserById(id);
			if (userOpt.isPresent()) {
				userService.deleteUser(id);
				status = HttpStatus.OK;
				return new ResortBookingResponse("User deleted successfully", status);
			} else {
				throw new ResortBookingException("User not found with ID: " + id);
			}
		} catch (ResortBookingException e) {
			message = e.getMessage();
		} catch (Exception e) {
			message = "Error deleting user: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}
}
