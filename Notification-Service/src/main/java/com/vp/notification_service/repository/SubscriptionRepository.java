package com.vp.notification_service.repository;

import com.vp.notification_service.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s.userId FROM Subscription s WHERE s.categoryId = :categoryId")
    List<Long> findUserIdsByCategoryId(Long categoryId);
}