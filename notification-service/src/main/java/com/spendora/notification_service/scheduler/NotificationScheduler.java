package com.spendora.notification_service.scheduler;

import com.spendora.notification_service.client.AnalyticsClient;
import com.spendora.notification_service.dto.ExpenseSummaryDTO;
import com.spendora.notification_service.service.NotificationService;
import com.spendora.notification_service.service.UserBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NotificationScheduler {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AnalyticsClient analyticsClient;

    @Autowired
    private UserBudgetService userBudgetService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void dailySpendingCheck() {

        // Todo: Hardcoded user (later from analytics service)
        String userId = "user123";

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();

        ExpenseSummaryDTO summary =
                analyticsClient.getMonthlySummary(userId, year, month);

        double monthlyLimit = userBudgetService.getMonthlyLimit(userId);

        if (summary.getTotalExpense() >= monthlyLimit * 0.8) {

            notificationService.createNotification(
                    userId,
                    "Spending Alert",
                    "You have used 80% of your monthly budget",
                    "WARNING"
            );
        }
    }
}
