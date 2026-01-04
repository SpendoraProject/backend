package com.spendora.notification_service.service.impl;

import com.spendora.notification_service.entity.Notification;
import com.spendora.notification_service.repo.NotificationRepository;
import com.spendora.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getUserNotifications(String userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setStatus("READ");
        notificationRepository.save(notification);
    }

    @Override
    public void createNotification(String userId, String title, String message, String type) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setStatus("SENT");
        notification.setCreatedAt(LocalDateTime.now());

        repository.save(notification);
    }
}
