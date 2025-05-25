package com.vp.notification_service.controller;

import com.vp.notification_service.dto.NotificationDto;
import com.vp.notification_service.repository.NotificationRepository;
import com.vp.notification_service.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Slf4j
@AllArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepo;
    private final NotificationService notificationService;


    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllNotifications(@RequestParam Long userId) {
        List<NotificationDto> notifications = notificationService.getAllNotificationByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/unread")
    public ResponseEntity<List<NotificationDto>> getAllUnreadNotifications(@RequestParam Long userId) {
        List<NotificationDto> unreadNotifications = notificationService.getAllUnreadNotificationByUserId(userId);
        return ResponseEntity.ok(unreadNotifications);
    }

    @PostMapping("/{id}/mark-read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        boolean updated = notificationService.markAsRead(id);
        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
