package com.vp.notification_service.service;

import com.vp.notification_service.dto.NotificationDto;
import com.vp.notification_service.model.Notification;

import java.util.List;

public interface NotificationSenderService {
    void sendNotificationToUser(Long userId, NotificationDto notificationDto);
}