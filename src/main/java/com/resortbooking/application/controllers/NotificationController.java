package com.resortbooking.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortbooking.application.dto.NotificationDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping
	public ResortBookingResponse createNotification(@RequestBody NotificationDTO dto) {
		String message = "";
		HttpStatus status = HttpStatus.OK;

		try {
			NotificationDTO createdNotification = notificationService.createNotification(dto);
			message = "Notification created successfully.";
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			message = "Error while creating notification: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}

	// 🔹 Get notifications by user ID
	@GetMapping("/user/{userId}")
	public ResortBookingResponse getNotificationsByUser(@PathVariable Long userId) {
		String message = "";
		HttpStatus status = HttpStatus.OK;

		try {
			List<NotificationDTO> notifications = notificationService.getNotificationsByUser(userId);
			if (notifications.isEmpty()) {
				message = "No notifications found for the user.";
				status = HttpStatus.NOT_FOUND;
			} else {
				message = "Notifications retrieved successfully.";
			}
		} catch (Exception e) {
			message = "Error while retrieving notifications: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}

	// 🔹 Delete notification by ID
	@DeleteMapping("/{id}")
	public ResortBookingResponse deleteNotification(@PathVariable Long id) {
		String message = "";
		HttpStatus status = HttpStatus.NO_CONTENT;

		try {
			notificationService.deleteNotification(id);
			message = "Notification deleted successfully.";
		} catch (Exception e) {
			message = "Error while deleting notification: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResortBookingResponse(message, status);
	}
}
