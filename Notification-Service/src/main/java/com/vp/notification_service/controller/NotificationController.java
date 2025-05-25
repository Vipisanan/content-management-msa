package com.vp.notification_service.controller;

import com.vp.notification_service.model.Notification;
import com.vp.notification_service.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Slf4j
@AllArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepo;

    @GetMapping
    public List<Notification> getNotifications(@RequestParam Long userId) {
        return notificationRepo.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @PostMapping("/{id}/mark-read")
    public void markAsRead(@PathVariable Long id) {
        notificationRepo.findById(id).ifPresent(n -> {
            n.setIsRead(true);
            notificationRepo.save(n);
        });
    }
}
