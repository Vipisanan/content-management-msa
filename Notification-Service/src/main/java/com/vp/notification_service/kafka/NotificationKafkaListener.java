package com.vp.notification_service.kafka;

import com.vp.notification_service.constant.AppConstant;
import com.vp.notification_service.mapper.NotificationMapper;
import com.vp.notification_service.model.Notification;
import com.vp.notification_service.repository.NotificationRepository;
import com.vp.notification_service.service.NotificationSenderService;
import com.vp.notification_service.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vp.notification_service.util.NotificationMessageUtil.buildReadableMessage;

@Service
@AllArgsConstructor
public class NotificationKafkaListener {

    private final NotificationRepository notificationRepo;
    private final SubscriptionService subscriptionService;
    private final NotificationSenderService notificationSenderService;

    @KafkaListener(topics = AppConstant.CONTENT_EVENT_KTP, groupId = "notification-service-group")
    public void handleContentEvent(ContentEvent event) {
        Set<Long> userIds = new HashSet<>();
        for (Long catId : event.getCategoryIds()) {
            userIds.addAll(subscriptionService.findUserIdsByCategoryId(catId));
        }
        userIds.remove(event.getWriterId()); // Exclude the writer

        LocalDateTime now = LocalDateTime.now();

        List<Notification> notifications = userIds.stream()
                .map(userId -> Notification.builder()
                        .userId(userId)
                        .contentId(event.getContentId())
                        .categoryId(null) // or choose a representative category, or store all, as needed
                        .type(event.getType())
                        .message(buildReadableMessage(event.getTitle(), event.getType()))
                        .isRead(false)
                        .createdAt(now)
                        .build())
                .toList();
        notificationSenderService.sendNotificationsToUsers(
                notifications.stream().map(NotificationMapper::toDto).collect(Collectors.toList())
        );
        notificationRepo.saveAll(notifications);
    }
}