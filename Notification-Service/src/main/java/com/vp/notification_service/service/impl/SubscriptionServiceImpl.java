package com.vp.notification_service.service.impl;

import com.vp.notification_service.repository.SubscriptionRepository;
import com.vp.notification_service.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public List<Long> findUserIdsByCategoryId(Long categoryId) {
        return subscriptionRepository.findUserIdsByCategoryId(categoryId);
    }
}
