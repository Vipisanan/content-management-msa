package com.vp.notification_service.service;

import com.vp.notification_service.dto.NotificationDto;

public interface NotificationSenderService {
    void sendNotificationToUser(Long userId, NotificationDto notificationDto);
}