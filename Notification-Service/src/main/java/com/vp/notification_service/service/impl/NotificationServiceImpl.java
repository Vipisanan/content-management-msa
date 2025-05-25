package com.vp.notification_service.service.impl;

import com.vp.notification_service.dto.NotificationDto;
import com.vp.notification_service.mapper.NotificationMapper;
import com.vp.notification_service.repository.NotificationRepository;
import com.vp.notification_service.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationDto> getAllNotificationByUserId(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream().map(NotificationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> getAllUnreadNotificationByUserId(Long userId) {
        return notificationRepository.findByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId)
                .stream().map(NotificationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean markAsRead(Long notificationId) {
        return notificationRepository.findById(notificationId).map(notification -> {
            notification.setIsRead(true);
            notificationRepository.save(notification);
            return true;
        }).orElse(false);
    }
}