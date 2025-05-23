package com.vp.notification_service.kafka;

import com.vp.notification_service.constant.AppConstant;
import com.vp.notification_service.model.Notification;
import com.vp.notification_service.repository.NotificationRepository;
import com.vp.notification_service.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationKafkaListener {

    private final NotificationRepository notificationRepo;
    private final SubscriptionService subscriptionService;

    @KafkaListener(topics = AppConstant.CONTENT_EVENT_KTP, groupId = "notification-service-group")
    public void handleContentEvent(ContentEvent event) {
        for (Long catId : event.getCategoryIds()) {
            List<Long> userIds = subscriptionService.findUserIdsByCategoryId(catId);
            for (Long userId : userIds) {
                Notification notification = Notification.builder()
                        .userId(userId)
                        .contentId(event.getContentId())
                        .categoryId(catId)
                        .type(event.getType())
                        .message("Content '" + event.getTitle() + "' was " + event.getType())
                        .isRead(false)
                        .build();
                notificationRepo.save(notification);
            }
        }
    }
}