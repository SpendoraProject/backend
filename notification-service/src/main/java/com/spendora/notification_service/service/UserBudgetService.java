package com.spendora.notification_service.service;

public interface UserBudgetService {
    double getMonthlyLimit(String userId);

    void setMonthlyLimit(String userId, double monthlyLimit);
}
