package com.vp.notification_service.service.impl;

import com.vp.notification_service.dto.NotificationDto;
import com.vp.notification_service.service.NotificationSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WebSocketNotificationSenderService implements NotificationSenderService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketNotificationSenderService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendNotificationToUser(Long userId, NotificationDto notificationDto) {
        messagingTemplate.convertAndSend("/topic/notifications." + userId, notificationDto);
        log.info("WebSocketNotificationSenderService : sent notification to /topic/notifications.{}:{}", userId, notificationDto.getMessage());
    }

    @Override
    public void sendNotificationsToUsers(List<NotificationDto> notifications) {
        if (notifications == null || notifications.isEmpty()) return;
        for (NotificationDto notification : notifications) {
            sendNotificationToUser(notification.getUserId(), notification);
        }
    }
}