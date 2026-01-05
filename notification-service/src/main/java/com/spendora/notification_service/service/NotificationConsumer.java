package com.spendora.notification_service.service;

public interface NotificationConsumer {
    void consumeOverspendEvent(String message);
}
