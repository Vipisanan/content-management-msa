package com.vp.notification_service.service.impl;

import com.vp.notification_service.dto.SubscriptionRequestDto;
import com.vp.notification_service.dto.SubscriptionResponseDto;
import com.vp.notification_service.dto.UserSubscriptionResponseDto;
import com.vp.notification_service.mapper.SubscriptionMapper;
import com.vp.notification_service.model.Subscription;
import com.vp.notification_service.repository.SubscriptionRepository;
import com.vp.notification_service.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionResponseDto subscribe(SubscriptionRequestDto requestDto) {
        if (subscriptionRepository.existsByUserIdAndCategoryId(requestDto.getUserId(), requestDto.getCategoryId())) {
            Subscription existing = subscriptionRepository.findByUserIdAndCategoryId(
                    requestDto.getUserId(), requestDto.getCategoryId());
            return SubscriptionMapper.toDto(existing, "Already subscribed.");
        }
        Subscription subscription = Subscription.builder()
                .userId(requestDto.getUserId())
                .categoryId(requestDto.getCategoryId())
                .build();
        Subscription saved = subscriptionRepository.save(subscription);
        return SubscriptionMapper.toDto(saved, "Subscribed successfully.");
    }

    @Override
    public SubscriptionResponseDto unsubscribe(SubscriptionRequestDto requestDto) {
        Subscription subscription = subscriptionRepository.findByUserIdAndCategoryId(
                requestDto.getUserId(), requestDto.getCategoryId());
        if (subscription == null) {
            Subscription dummy = Subscription.builder()
                    .userId(requestDto.getUserId())
                    .categoryId(requestDto.getCategoryId())
                    .build();
            return SubscriptionMapper.toDto(dummy, "Not subscribed.");
        }
        subscriptionRepository.delete(subscription);
        return SubscriptionMapper.toDto(subscription, "Unsubscribed successfully.");
    }

    @Override
    public List<Long> findUserIdsByCategoryId(Long categoryId) {
        return subscriptionRepository.findUserIdsByCategoryId(categoryId);
    }

    @Override
    public List<UserSubscriptionResponseDto> getAllSubscriptionsByUserId(Long userId) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        return subscriptions.stream()
                .map(SubscriptionMapper::toDto)
                .collect(Collectors.toList());
    }
}
