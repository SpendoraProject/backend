package com.spendora.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NotificationEventDTO {
    private String userId;
    private String type;
    private String message;
}
