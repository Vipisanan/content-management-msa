package com.vp.notification_service.controller;

import com.vp.notification_service.dto.SubscriptionRequestDto;
import com.vp.notification_service.dto.SubscriptionResponseDto;
import com.vp.notification_service.dto.UserSubscriptionResponseDto;
import com.vp.notification_service.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<SubscriptionResponseDto> subscribe(@RequestBody SubscriptionRequestDto requestDto) {
        return ResponseEntity.ok(subscriptionService.subscribe(requestDto));
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<SubscriptionResponseDto> unsubscribe(@RequestBody SubscriptionRequestDto requestDto) {
        return ResponseEntity.ok(subscriptionService.unsubscribe(requestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserSubscriptionResponseDto>> getAllSubscriptionsByUserId(@PathVariable Long userId) {
        List<UserSubscriptionResponseDto> subscriptions = subscriptionService.getAllSubscriptionsByUserId(userId);
        return ResponseEntity.ok(subscriptions);
    }
}