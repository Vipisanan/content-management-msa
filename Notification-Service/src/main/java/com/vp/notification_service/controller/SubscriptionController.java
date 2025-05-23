package com.vp.notification_service.controller;

import com.vp.notification_service.dto.SubscriptionRequestDto;
import com.vp.notification_service.dto.SubscriptionResponseDto;
import com.vp.notification_service.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}