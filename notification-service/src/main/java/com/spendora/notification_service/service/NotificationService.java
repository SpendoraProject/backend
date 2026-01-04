package com.spendora.notification_service.service;

import com.spendora.notification_service.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getUserNotifications(String userId);

    void markAsRead(Long id);

    void createNotification(String userId, String title, String message, String type);
}
