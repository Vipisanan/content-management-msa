package com.vp.notification_service.service;

import com.vp.notification_service.dto.NotificationDto;

import java.util.List;

public interface NotificationSenderService {
    void sendNotificationToUser(Long userId, NotificationDto notificationDto);

    void sendNotificationsToUsers(List<NotificationDto> notifications);

}