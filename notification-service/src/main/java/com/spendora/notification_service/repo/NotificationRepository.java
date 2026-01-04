package com.spendora.notification_service.repo;


import com.spendora.notification_service.entity.Notification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository {
    List<Notification> findByUserIdOrderByCreatedAtDesc(String userId);
}
