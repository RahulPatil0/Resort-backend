package com.resortbooking.application.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.NotificationRepository;
import com.resortbooking.application.dao.UserRepository;
import com.resortbooking.application.dto.NotificationDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.NotificationMapper;
import com.resortbooking.application.models.Notification;
import com.resortbooking.application.models.User;
import com.resortbooking.application.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationMapper mapper;

    @Override
    public NotificationDTO createNotification(NotificationDTO dto) throws ResortBookingException{
        try {
            Optional<User> userOpt = userRepository.findById(dto.getUserId());
            if (userOpt.isEmpty()) throw new RuntimeException("User not found");
            Notification notification = mapper.toEntity(dto);
            notification.setUser(userOpt.get());
            return mapper.toDto(notificationRepository.save(notification));
        } catch (Exception e) {
            throw new ResortBookingException("Failed to create notification: " + e.getMessage());
        }
    }

    @Override
    public List<NotificationDTO> getNotificationsByUser(Long userId) throws ResortBookingException{
        try {
            return notificationRepository.findByUserId(userId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResortBookingException("Error retrieving notifications: " + e.getMessage());
        }
    }

    @Override
    public NotificationDTO markAsRead(Long id) throws ResortBookingException{
        try {
            Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
            notification.setRead(true);
            return mapper.toDto(notificationRepository.save(notification));
        } catch (Exception e) {
            throw new ResortBookingException("Failed to update notification: " + e.getMessage());
        }
    }

    @Override
    public void deleteNotification(Long id) throws ResortBookingException{
        try {
            notificationRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResortBookingException("Failed to delete notification: " + e.getMessage());
        }
    }
}
