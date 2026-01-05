package com.spendora.notification_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spendora.notification_service.service.NotificationConsumer;
import com.spendora.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumerImpl implements NotificationConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper; // Injected Jackson mapper

    @Override
    @KafkaListener(topics = "spending-alerts", groupId = "notification-group")
    public void consumeOverspendEvent(String message) {
        try {
            // Convert JSON string to JsonNode
            JsonNode json = objectMapper.readTree(message);

            String userId = json.get("userId").asText();
            String month = json.get("month").asText();
            double totalExpense = json.get("totalExpense").asDouble();

            System.out.println("Received overspend event for user: " + userId);

            notificationService.createNotification(
                    userId,
                    "Spending Alert",
                    "You have exceeded your monthly budget for " + month,
                    "WARNING"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
