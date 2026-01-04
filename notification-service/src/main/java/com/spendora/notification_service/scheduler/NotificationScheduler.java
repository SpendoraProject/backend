package com.spendora.notification_service.scheduler;

import com.spendora.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationScheduler {

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void dailySpendingCheck() {

        // Todo: Hardcoded user (later from analytics service)
        String userId = "1";

        // TEMP logic
        boolean overspent = true;

        if (overspent) {
            notificationService.createNotification(
                    userId,
                    "Spending Alert",
                    "You have exceeded 80% of your monthly budget",
                    "WARNING"
            );
        }
    }
}
