package com.spendora.notification_service.controller;

import com.spendora.notification_service.dto.UserBudgetDTO;
import com.spendora.notification_service.service.UserBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
public class UserBudgetController {

    @Autowired
    private UserBudgetService userBudgetService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserBudgetDTO> getBudget(@PathVariable String userId) {
        double limit = userBudgetService.getMonthlyLimit(userId);
        return ResponseEntity.ok(new UserBudgetDTO(userId, limit));
    }

    // Update or set budget for a user
    @PostMapping("/{userId}")
    public ResponseEntity<UserBudgetDTO> setBudget(
            @PathVariable String userId,
            @RequestParam double monthlyLimit) {

        userBudgetService.setMonthlyLimit(userId, monthlyLimit);
        return ResponseEntity.ok(new UserBudgetDTO(userId, monthlyLimit));
    }
}
