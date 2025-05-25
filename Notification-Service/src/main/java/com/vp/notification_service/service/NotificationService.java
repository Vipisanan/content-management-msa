package com.vp.notification_service.service;


import com.vp.notification_service.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAllNotificationByUserId(Long userId);

    List<NotificationDto> getAllUnreadNotificationByUserId(Long userId);

    boolean markAsRead(Long notificationId);
}
