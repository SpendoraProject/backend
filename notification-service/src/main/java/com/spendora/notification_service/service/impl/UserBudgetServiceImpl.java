package com.spendora.notification_service.service.impl;

import com.spendora.notification_service.dto.UserBudgetDTO;
import com.spendora.notification_service.repo.UserBudgetRepository;
import com.spendora.notification_service.service.UserBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBudgetServiceImpl implements UserBudgetService {

    @Autowired
    private UserBudgetRepository userBudgetRepository;


    public double getMonthlyLimit(String userId) {
        return userBudgetRepository.findById(Long.valueOf(userId))
                .map(UserBudgetDTO::getMonthlyLimit)
                .orElse(50000.0); // default if not set
    }

    public void setMonthlyLimit(String userId, double limit) {
        UserBudgetDTO budget = UserBudgetDTO.builder()
                .userId(userId)
                .monthlyLimit(limit)
                .build();
        userBudgetRepository.save(budget);
    }
}
